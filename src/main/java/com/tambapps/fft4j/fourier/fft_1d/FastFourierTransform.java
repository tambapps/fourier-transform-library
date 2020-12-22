package com.tambapps.fft4j.fourier.fft_1d;

import com.tambapps.fft4j.complex.vector.CVector;

/**
 * Interface used to implement differents Fourier
 * Transform algorithms
 */
public interface FastFourierTransform {

  /**
   * Computes the Fourier transform in the
   * given vector
   *
   * @param vector the input of the FT
   */
  void compute(CVector vector);

  /**
   * Computes the Fourier transform in the
   * returned vector
   *
   * @param vector the input of the FT
   * @return the FFT vector
   */
  CVector computeCopy(CVector vector);

  /**
   * Same as comput (util for groovy operator)
   *
   * @param vector the input of the FT
   */
  void call(CVector vector);

  /**
   * Provides the name of the algorithm
   *
   * @return the name of the algorithm
   */
  String getName();

  /**
   * Provides a description of the algorithm
   *
   * @return the description of the algorithm
   */
  String getDescription();
}
