package com.tambapps.fft4j.fourier.fft_1d;

import com.tambapps.fft4j.complex.vector.CVector;

/**
 * Fast Fourier Inverse Algorithm.
 * Interface used to compute Fourier Inverse.
 */
public interface FastFourierInverse {

  /**
   * Computes the fourier inverse in the given vector
   * with the provided FFT algorithm
   *
   * @param vector    the input of the fourier inverse
   */
  void compute(CVector vector);

  /**
   * same as compute (util for groovy operator)
   *
   * @param vector    the input of the fourier inverse
   */
  void call(CVector vector);

}
