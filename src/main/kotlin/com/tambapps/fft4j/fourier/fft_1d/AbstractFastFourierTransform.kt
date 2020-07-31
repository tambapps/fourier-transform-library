package com.tambapps.fft4j.fourier.fft_1d

import com.tambapps.fft4j.complex.vector.CVector

// need to override computeCopy OR compute
abstract class AbstractFastFourierTransform: FastFourierTransform {


    override fun invoke(vector: CVector) {
        computeIn(vector)
    }

    override fun compute(vector: CVector): CVector {
        return vector.copy().also {
            computeIn(it)
        }
    }

    override fun computeIn(vector: CVector) {
        val result: CVector = compute(vector)
        result.copy(vector)
    }


}

