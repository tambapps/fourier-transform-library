package com.tambapps.fft4j.fourier.fft_1d

import com.tambapps.fft4j.complex.Complex
import com.tambapps.fft4j.fourier.util.Utils.get2Exponent


/**
 * Class that implements multiple 1D FFT algorithms
 */
object FourierAlgorithms {
    /**
     * A basic implementation of the FFT <br></br>
     * Implemented the computation like in the basic formula
     */
    val BASIC: FFTAlgorithm = BasicFFT()
    /**
     * Cooley-Tukey algorithm implemented recursively. <br></br>
     * Input size must be a power of two
     */
    val CT_RECURSIVE: FFTAlgorithm = CooleyTukeyRecursiveFFT()
    /**
     * Cooley-Tukey algorithm implemented iteratively. <br></br>
     * Input size must be a power of two
     */
    val CT_ITERATIVE: FFTAlgorithm = CooleyTukeyIterativeFFT()

    /**
     * Inverse algorithm. The inverse algorithm use a FFT algorithm given
     * as a parameter.
     */
    fun inverse(algorithm: FFTAlgorithm): FFITAlgorithm {
        return FFITAlgorithmImpl(algorithm)
    }

}
