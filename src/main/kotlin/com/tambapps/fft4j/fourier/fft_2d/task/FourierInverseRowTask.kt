package com.tambapps.fft4j.fourier.fft_2d.task

import com.tambapps.fft4j.complex.array2d.CArray2D
import com.tambapps.fft4j.complex.vector.CVector
import com.tambapps.fft4j.fourier.fft_1d.FastFourierTransform
import com.tambapps.fft4j.fourier.fft_1d.FourierAlgorithms

class FourierInverseRowTask(fft: FastFourierTransform, data: CArray2D, index: Int) : AbstractFourierRowTask(fft, data, index) {

    override fun computeVector(vector: CVector) {
        FourierAlgorithms.inverse(fft).compute(vector)
    }
}