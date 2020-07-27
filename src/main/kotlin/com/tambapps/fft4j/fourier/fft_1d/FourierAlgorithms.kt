package com.tambapps.fft4j.fourier.fft_1d


/**
 * Class that implements multiple 1D FFT algorithms
 */
object FourierAlgorithms {
    /**
     * A basic implementation of the FFT <br></br>
     * Implemented the computation like in the basic formula
     */
    val BASIC: FastFourierTransform = BasicFFT()
    /**
     * Cooley-Tukey algorithm implemented recursively. <br></br>
     * Input size must be a power of two
     */
    val CT_RECURSIVE: FastFourierTransform = CooleyTukeyRecursiveFFT()
    /**
     * Cooley-Tukey algorithm implemented iteratively. <br></br>
     * Input size must be a power of two
     */
    val CT_ITERATIVE: FastFourierTransform = CooleyTukeyIterativeFFT()

    /**
     * Inverse algorithm. The inverse algorithm use a FFT algorithm given
     * as a parameter.
     */
    fun inverse(fft: FastFourierTransform): FastFourierInverse {
        return FastFourierInverseImpl(fft)
    }

}
