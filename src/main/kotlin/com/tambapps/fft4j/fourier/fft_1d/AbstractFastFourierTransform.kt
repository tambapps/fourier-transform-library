package com.tambapps.fft4j.fourier.fft_1d

import com.tambapps.fft4j.complex.vector.CVector

// need to override computeCopy OR compute
abstract class AbstractFastFourierTransform: FastFourierTransform {


    override fun invoke(vector: CVector) {
        compute(vector)
    }

    override fun computeCopy(vector: CVector): CVector {
        return vector.copy().also {
            compute(it)
        }
    }

    override fun compute(vector: CVector) {
        val result: CVector = computeCopy(vector)
        result.copy(vector)
    }


}

