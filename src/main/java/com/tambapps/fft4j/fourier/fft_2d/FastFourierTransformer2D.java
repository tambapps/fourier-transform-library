package com.tambapps.fft4j.fourier.fft_2d;

import com.tambapps.fft4j.complex.array2d.CArray2D;
import com.tambapps.fft4j.fourier.fft_1d.FastFourierTransform;
import com.tambapps.fft4j.fourier.fft_1d.FourierAlgorithms;
import com.tambapps.fft4j.fourier.util.Utils;
import com.tambapps.fft4j.complex.vector.CVector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This is the class that applies 2D Fast Fourier Transform
 * by applying the 1D FFT independently on each row and then on each column (concurrently)
 */
public class FastFourierTransformer2D {

  public static final FastFourierElector DEFAULT_CHOOSER = (M, N) -> Utils.is2Power(M) && Utils.is2Power(N) ?
    FourierAlgorithms.CT_RECURSIVE :
    FourierAlgorithms.BASIC;

  private final ExecutorService executorService;
  private FastFourierElector chooser;

  public FastFourierTransformer2D() {
    this(Runtime.getRuntime().availableProcessors() + 1);
  }

  /**
   * Creates a FFT2DComputer with the an executor having the specified number of threads
   *
   * @param nbThreads the number of threads used by the executor
   */
  public FastFourierTransformer2D(int nbThreads) {
    chooser = DEFAULT_CHOOSER;
    executorService = Executors.newFixedThreadPool(nbThreads);
  }

  /**
   * Creates a FFT2DComputer that will use the given ExecutorService to process FFT
   * @param service the executor service
   */
  public FastFourierTransformer2D(ExecutorService service) {
    this.executorService = service;
    chooser = DEFAULT_CHOOSER;
  }

  /**
     * Computes the FFT in the given array, with the given FFT algorithm
     *
     * @param f         the input
     * @param algorithm the algorithm used for the computation
     * @return true if it was a success
     */
  public boolean transform(CArray2D f, FastFourierTransform algorithm) {
    return compute(f, false, true, algorithm) && compute(f, false, false, algorithm);
  }

  /**
   * Computes the FFT in the given array, and a chosen FFTAlgorithm based on input sizes
   *
   * @param f the input
   * @return true if it was a success
   */
  public boolean transform(CArray2D f) {
    return transform(f, chooser.elect(f.getM(), f.getN()));
  }

  /**
   * Computes the FFT inverse in the given array, with the given FFT algorithm
   *
   * @param f         the input
   * @param algorithm the algorithm used for the computation
   * @return true if it was a success
   */
  public boolean inverse(CArray2D f, FastFourierTransform algorithm) {
    return compute(f, true, true, algorithm) && compute(f, true, false, algorithm);
  }

  /**
   * Computes the FFT inverse in the given array, and a chosen FFTAlgorithm based on input sizes
   *
   * @param f the input
   * @return true if it was a success
   */
  public boolean inverse(CArray2D f) {
    return inverse(f, chooser.elect(f.getM(), f.getN()));
  }

  private boolean compute(CArray2D f, final boolean inverse, final boolean row,
                          FastFourierTransform algorithm) {
    List<Future<?>> futures = new ArrayList<>();
    int count = row ? f.getM() : f.getN();

    for (int i = 0; i < count; i++) {
      if (inverse) {
        futures.add(executorService.submit(new InverseTask(algorithm, f, i, row)));
      } else {
        futures.add(executorService.submit(new TransformTask(algorithm, f, i, row)));
      }
    }
    boolean success = true;
    for (int i = 0; i < count; i++) {
      try {
        futures.get(i).get();
      } catch (InterruptedException | ExecutionException e) {
        success = false;
      }
    }
    return success;
  }

  /**
   * Sets the algorithm chooser
   *
   * @param chooser the new algorithm chooser
   */
  public void setChooser(FastFourierElector chooser) {
    this.chooser = chooser;
  }


  private abstract class FourierTask implements Runnable {

    protected final FastFourierTransform algorithm;
    private final CArray2D data;
    private final int index;
    private final boolean row;

    FourierTask(FastFourierTransform algorithm, CArray2D data, int index, boolean row) {
      this.algorithm = algorithm;
      this.data = data;
      this.index = index;
      this.row = row;
    }

    @Override
    public final void run() {
      if (row) {
        computeVector(data.getRow(index));
      } else {
        computeVector(data.getColumn(index));
      }
    }

    abstract void computeVector(CVector vector);
  }


  /**
   * Task that will compute the FFT for many columns/rows
   */
  private class TransformTask extends FourierTask {

    TransformTask(FastFourierTransform algorithm, CArray2D data, int i, boolean row) {
      super(algorithm, data, i, row);
    }

    @Override
    void computeVector(CVector vector) {
      algorithm.compute(vector);
    }

  }


  /**
   * Task that will compute the inverse FFT for a given row/column
   */
  private class InverseTask extends FourierTask {

    InverseTask(FastFourierTransform algorithm, CArray2D data, int i, boolean row) {
      super(algorithm, data, i, row);
    }

    @Override
    void computeVector(CVector vector) {
      FourierAlgorithms.inverse(algorithm).compute(vector);
    }

  }

}
