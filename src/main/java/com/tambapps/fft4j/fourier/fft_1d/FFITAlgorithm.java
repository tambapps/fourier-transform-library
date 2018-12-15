package com.tambapps.fft4j.fourier.fft_1d;

import com.tambapps.fft4j.util.CVector;

/**
 * Fast Fourier Inverse Algorithm.
 * Interface used to compute Fourier Inverse.
 */
public interface FFITAlgorithm {

  /**
   * Computes the fourier inverse in the given vector
   * with the provided FFT algorithm
   *
   * @param vector    the input of the fourier inverse
   * @param algorithm the algorithm used by the fourier inverse
   */
  void compute(CVector vector, FFTAlgorithm algorithm);

  /**
   * same as compute (util for groovy)
   *
   * @param vector    the input of the fourier inverse
   * @param algorithm the algorithm used by the fourier inverse
   */
  void call(CVector vector, FFTAlgorithm algorithm);

}
