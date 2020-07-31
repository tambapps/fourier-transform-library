package com.tambapps.fft4j.fourier.fft_1d

import com.tambapps.fft4j.complex.vector.CVector


/**
 * Fast Fourier Inverse Algorithm.
 * Interface used to compute Fourier Inverse.
 */
interface FastFourierInverse {
    /**
     * Computes the fourier inverse in the given vector
     * with the provided FFT algorithm
     *
     * @param vector    the input of the fourier inverse
     */
    fun compute(vector: CVector)

    /**
     * same as compute
     *
     * @param vector    the input of the fourier inverse
     */
    operator fun invoke(vector: CVector)
}