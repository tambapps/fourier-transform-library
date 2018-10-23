package com.tambapps.math.fourier.fft_1d;

import com.tambapps.math.util.CVector;

/**
 * Interface used to implement differents Fourier
 * Transform algorithms
 */
public interface FFTAlgorithm {

  /**
   * Computes the Fourier transform in the
   * given vector
   * @param vector the input of the FT
   */
  void compute(CVector vector);

  /**
   * Same as comput (util for groovy)
   * @param vector the input of the FT
   */
  void call(CVector vector);

  /**
   * Provides the name of the algorithm
   * @return the name of the algorithm
   */
  String getName();

  /**
   * Provides a description of the algorithm
   * @return the description of the algorithm
   */
  String getDescription();
}
