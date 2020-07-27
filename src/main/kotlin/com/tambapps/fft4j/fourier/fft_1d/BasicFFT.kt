package com.tambapps.fft4j.fourier.fft_1d

import com.tambapps.fft4j.complex.Complex
import com.tambapps.fft4j.complex.vector.ArrayCVector
import com.tambapps.fft4j.complex.vector.CVector

internal class BasicFFT: AbstractFastFourierTransform() {

    override val name = "Basic"
    override val description = "Compute the sums like in the basic FFT formula"

    override fun computeCopy(vector: CVector): CVector {
        val result: CVector = ArrayCVector(vector.size)
        val N = vector.size.toDouble()
        for (k in 0 until vector.size) {
            var sum = Complex.ZERO
            for (n in 0 until vector.size) {
                sum += vector[n] * Complex.expI(-2.0 * Math.PI * k.toDouble() * n.toDouble() / N)
            }
            result[k] = sum
        }
        return result
    }
}