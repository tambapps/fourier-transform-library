package com.tambapps.fft4j.fourier.fft_2d;

import com.tambapps.fft4j.carray2d.CArray2D;
import com.tambapps.fft4j.fourier.fft_1d.FFTAlgorithm;
import com.tambapps.fft4j.fourier.fft_1d.FourierAlgorithms;
import com.tambapps.fft4j.fourier.util.Utils;
import com.tambapps.fft4j.util.CVector;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;

/**
 * This is the class that applies 2D Fast Fourier Transform
 * by applying the 1D FFT independently on each row and then on each column (concurrently)
 */
public class FastFourierTransformer2D {

  public static final AlgorithmChooser DEFAULT_CHOOSER = new AlgorithmChooser() {
    @Override
    public FFTAlgorithm getAlgorithm(int M, int N) {
      return Utils.is2Power(M) && Utils.is2Power(N) ?
        FourierAlgorithms.CT_RECURSIVE :
        FourierAlgorithms.BASIC;
    }
  };

  private final double maxThreads;
  private final ExecutorCompletionService<Boolean> executorService;
  private AlgorithmChooser chooser;

  /**
   * Creates a FFT2DComputer with the given executor
   *
   * @param executor the executor that will execute tasks
   */
  public FastFourierTransformer2D(ExecutorService executor) {
    this(executor, Runtime.getRuntime().availableProcessors() + 1);
  }

  /**
   * Creates a FFT2DComputer with the given executor
   *
   * @param executor   the executor that will execute tasks
   * @param maxThreads the max number of threads that can be used for computing
   */
  public FastFourierTransformer2D(ExecutorService executor, int maxThreads) {
    executorService = new ExecutorCompletionService<>(executor);
    this.maxThreads = maxThreads;
    chooser = DEFAULT_CHOOSER;
  }

  /**
   * Computes the FFT in the given array, with the given FFT algorithm
   *
   * @param f         the input
   * @param algorithm the algorithm used for the computation
   * @return true if it was a success
   */
  public boolean transform(CArray2D f, FFTAlgorithm algorithm) {
    return compute(f, false, true, algorithm) && compute(f, false, false, algorithm);
  }

  /**
   * Computes the FFT in the given array, and a chosen FFTAlgorithm based on input sizes
   *
   * @param f the input
   * @return true if it was a success
   */
  public boolean transform(CArray2D f) {
    return transform(f, chooser.getAlgorithm(f.getM(), f.getN()));
  }

  /**
   * Computes the FFT inverse in the given array, with the given FFT algorithm
   *
   * @param f         the input
   * @param algorithm the algorithm used for the computation
   * @return true if it was a success
   */
  public boolean inverse(CArray2D f, FFTAlgorithm algorithm) {
    return compute(f, true, true, algorithm) && compute(f, true, false, algorithm);
  }

  /**
   * Computes the FFT inverse in the given array, and a chosen FFTAlgorithm based on input sizes
   *
   * @param f the input
   * @return true if it was a success
   */
  public boolean inverse(CArray2D f) {
    return inverse(f, chooser.getAlgorithm(f.getM(), f.getN()));
  }

  private boolean compute(CArray2D f, final boolean inverse, final boolean row,
                          FFTAlgorithm algorithm) {
    int treated = 0;
    int max = row ? f.getM() : f.getN();
    int perThread = (int) Math.floor(((double) max) / maxThreads);
    int count = 0;

    while (treated < max) {
      if (inverse) {
        executorService.submit(
          new InverseTask(algorithm, f, treated, Math.min(max, treated + perThread), row));
      } else {
        executorService.submit(new TransformTask(algorithm, f, treated,
          Math.min(max, treated + perThread), row));
      }

      treated += perThread;
      count++;
    }

    boolean success = true;
    for (int i = 0; i < count; i++) {
      try {
        executorService.take().get();
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
  public void setChooser(AlgorithmChooser chooser) {
    this.chooser = chooser;
  }


  private abstract class FourierTask implements Callable<Boolean> {

    protected final FFTAlgorithm algorithm;
    private final CArray2D data;
    private final int from;
    private final int to;
    private final boolean row;

    FourierTask(FFTAlgorithm algorithm, CArray2D data, int from, int to, boolean row) {
      this.algorithm = algorithm;
      this.data = data;
      this.from = from;
      this.to = to;
      this.row = row;
    }

    @Override
    public final Boolean call() {
      if (row) {
        for (int i = from; i < to; i++) {
          computeVector(data.getRow(i));
        }
      } else {
        for (int i = from; i < to; i++) {
          computeVector(data.getColumn(i));
        }
      }
      return true;
    }

    abstract void computeVector(CVector vector);
  }


  /**
   * Task that will compute the FFT for many columns/rows
   */
  private class TransformTask extends FourierTask {

    TransformTask(FFTAlgorithm algorithm, CArray2D data, int from, int to, boolean row) {
      super(algorithm, data, from, to, row);
    }

    @Override
    void computeVector(CVector vector) {
      algorithm.compute(vector);
    }

  }


  /**
   * Task that will compute the inverse FFT for many columns/rows
   */
  private class InverseTask extends FourierTask {

    InverseTask(FFTAlgorithm algorithm, CArray2D data, int from, int to, boolean row) {
      super(algorithm, data, from, to, row);
    }

    @Override
    void computeVector(CVector vector) {
      FourierAlgorithms.INVERSE.compute(vector, algorithm);
    }

  }

}
