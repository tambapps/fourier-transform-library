package com.tambapps.fft4j.fourier.fft_1d

import com.tambapps.fft4j.complex.vector.CVector

class FFITAlgorithmImpl(private val algorithm: FFTAlgorithm): FFITAlgorithm {

    override fun compute(vector: CVector) {
        for (i in 0 until vector.size) {
            vector[i] = vector[i].conj()
        }

        algorithm.compute(vector)

        val iN = 1.0 / vector.size.toDouble()
        for (i in 0 until vector.size) {
            vector[i] = vector[i].conj() * iN
        }
    }

    override fun invoke(vector: CVector) {
        compute(vector)
    }
}