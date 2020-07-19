package com.tambapps.fft4j.fourier.fft_2d.task

import com.tambapps.fft4j.complex.array2d.CArray2D
import com.tambapps.fft4j.complex.vector.CVector
import com.tambapps.fft4j.fourier.fft_1d.FFTAlgorithm


abstract class AbstractFourierRowTask(protected val algorithm: FFTAlgorithm,
                                      private val data: CArray2D,
                                      private val index: Int): Runnable {

    override fun run() {
        computeVector(data.getRow(index))
    }

    abstract fun computeVector(vector: CVector)

}