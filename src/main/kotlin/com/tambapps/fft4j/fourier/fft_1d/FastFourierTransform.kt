package com.tambapps.fft4j.fourier.fft_1d

import com.tambapps.fft4j.complex.vector.CVector

/**
 * Interface used to implement differents Fourier
 * Transform algorithms
 */
interface FastFourierTransform {
    /**
     * Computes the Fourier transform in the
     * given vector
     *
     * @param vector the input of the FT
     */
    fun compute(vector: CVector)

    /**
     * Computes the Fourier transform in the
     * returned vector
     *
     * @param vector the input of the FT
     * @return the FFT vector
     */
    fun computeCopy(vector: CVector): CVector

    /**
     * Same as compute (util for groovy operator)
     *
     * @param vector the input of the FT
     */
    operator fun invoke(vector: CVector)

    /**
     * Provides the name of the algorithm
     *
     * @return the name of the algorithm
     */
    val name: String

    /**
     * Provides a description of the algorithm
     *
     * @return the description of the algorithm
     */
    val description: String
}
