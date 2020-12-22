package com.tambapps.fft4j.fourier.fft_1d;

/**
 * Class that implements multiple 1D FFT algorithms
 */
public final class FastFouriers {

  /**
   * A basic implementation of the FFT <br>
   * Implemented the computation like in the basic formula
   */
  public static final FastFourierTransform BASIC = new BasicFFT();
  /**
   * Cooley-Tukey algorithm implemented recursively. <br>
   * Input size must be a power of two
   */
  public static final FastFourierTransform CT_RECURSIVE = new CooleyTukeyRecursiveFFT();

  /**
   * Cooley-Tukey algorithm implemented iteratively. <br>
   * Input size must be a power of two
   */
  public static final FastFourierTransform CT_ITERATIVE = new CooleyTukeyIterativeFFT();

  /**
   * Inverse algorithm. The inverse algorithm use a FFT algorithm given
   * as a parameter.
   */
  public static FastFourierInverse inverse(FastFourierTransform algorithm) {
    return new FastFourierInverseImpl(algorithm);
  }

}
