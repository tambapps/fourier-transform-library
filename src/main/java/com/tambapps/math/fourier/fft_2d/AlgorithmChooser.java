package com.tambapps.math.fourier.fft_2d;

import com.tambapps.math.fourier.fft_1d.FFTAlgorithm;

/**
 * Class that choose a FFT Algorithm given a 2D array size
 */
interface AlgorithmChooser {

  /**
   * Get an algorithm given the dimensions of a 2D array
   *
   * @param M the 1st dimension of the 2D array
   * @param N the 2nd dimension of the 2D array
   * @return the FFT algorithm
   */
  FFTAlgorithm getAlgorithm(int M, int N);

}
