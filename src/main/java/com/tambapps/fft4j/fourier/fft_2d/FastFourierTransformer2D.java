package com.tambapps.fft4j.fourier.fft_2d;

import com.tambapps.fft4j.complex.array2d.CArray2D;
import com.tambapps.fft4j.complex.vector.CVector;
import com.tambapps.fft4j.fourier.fft_1d.FastFourierTransform;
import com.tambapps.fft4j.fourier.fft_1d.FastFouriers;
import com.tambapps.fft4j.fourier.fft_2d.task.FourierInverseTask;
import com.tambapps.fft4j.fourier.fft_2d.task.FourierTransformTask;
import com.tambapps.fft4j.fourier.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

/**
 * This is the class that applies 2D Fast Fourier Transform
 * by applying the 1D FFT independently on each row and then on each column (concurrently)
 */
public class FastFourierTransformer2D {

  public static final FastFourierElector DEFAULT_CHOOSER = (M, N) -> Utils.is2Power(M) && Utils.is2Power(N) ?
    FastFouriers.CT_RECURSIVE :
    FastFouriers.BASIC;

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
    return compute(false, f.getM(), f::getRow, algorithm) && compute(false, f.getN(), f::getColumn, algorithm);
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
    return compute(true, f.getM(), f::getRow, algorithm) && compute(true, f.getN(), f::getColumn, algorithm);
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

  private boolean compute(final boolean inverse, int count, Function<Integer, CVector> vectorExtractor,
                          FastFourierTransform algorithm) {
    List<Future<?>> futures = new ArrayList<>();

    for (int i = 0; i < count; i++) {
      if (inverse) {
        futures.add(executorService.submit(new FourierInverseTask(vectorExtractor.apply(i) ,algorithm)));
      } else {
        futures.add(executorService.submit(new FourierTransformTask(vectorExtractor.apply(i) ,algorithm)));
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

}
