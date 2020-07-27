package com.tambapps.fft4j.fourier.fft_2d.chooser

import com.tambapps.fft4j.fourier.fft_1d.FastFourierTransform

/**
 * Class that choose a FFT Algorithm given a 2D array size
 */
interface FastFourierChooser {
    /**
     * Get an algorithm given the dimensions of a 2D array
     *
     * @param M the 1st dimension of the 2D array
     * @param N the 2nd dimension of the 2D array
     * @return the FFT algorithm
     */
    fun getFastFourierTransform(M: Int, N: Int): FastFourierTransform
}
