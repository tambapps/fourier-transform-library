package com.tambapps.fft4j.fourier.fft_2d.task

import com.tambapps.fft4j.complex.array2d.CArray2D
import com.tambapps.fft4j.complex.vector.CVector
import com.tambapps.fft4j.fourier.fft_1d.FastFourierTransform


abstract class AbstractFourierColumnTask(protected val fft: FastFourierTransform,
                                         private val data: CArray2D,
                                         private val index: Int): Runnable {

    override fun run() {
        computeVector(data.getColumn(index))
    }

    abstract fun computeVector(vector: CVector)

}