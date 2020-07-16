package com.tambapps.fft4j.fourier.fft_1d

import com.tambapps.fft4j.complex.Complex
import com.tambapps.fft4j.complex.vector.ArrayCVector
import com.tambapps.fft4j.complex.vector.CVector

class CooleyTukeyRecursiveFFT: AbstractFFTAlgorithm() {

    override val name = "Cooley-Tukey recursive"
    override val description = "Cooley-Tukey algorithm implemented recursively (input array must have power of 2 sizes)"

    override fun computeCopy(vector: CVector): CVector {
        val N: Int = vector.size
        if (N <= 1) {
            return vector
        }
        val evens = computeCopy(evensCopy(vector))
        val odds = computeCopy(oddsCopy(vector))

        val T = Array(N / 2) { i -> odds[i].times(Complex.expI(-2.0 * Math.PI * i.toDouble() / N.toDouble())) }

        val result: CVector = ArrayCVector(N)
        for (i in 0 until N / 2) {
            result[i] = evens[i] + T[i]
            result[i + N / 2] = evens[i] - T[i]
        }
        return result
    }


    private fun evensCopy(vector: CVector): CVector {
        val size: Int = (vector.size + 1) / 2
        return copy(vector, size)
    }

    private fun oddsCopy(vector: CVector): CVector {
        val size: Int = vector.size / 2
        return copy(vector, size)
    }
    
    private fun copy(vector: CVector, size: Int): CVector {
        val copy: CVector = ArrayCVector(size)
        var count = 0
        var i = 1
        while (i < vector.size) {
            copy[count] = vector[i]
            count++
            i += 2
        }
        return copy
    }
}