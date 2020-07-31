package com.tambapps.fft4j.fourier.fft_2d.task

import com.tambapps.fft4j.complex.array2d.CArray2D
import com.tambapps.fft4j.complex.vector.CVector
import com.tambapps.fft4j.fourier.fft_1d.FastFourierTransform

class FourierTransformColumnTask(fft: FastFourierTransform, data: CArray2D, index: Int) : AbstractFourierColumnTask(fft, data, index) {

    override fun computeVector(vector: CVector) {
        fft.computeIn(vector)
    }
}