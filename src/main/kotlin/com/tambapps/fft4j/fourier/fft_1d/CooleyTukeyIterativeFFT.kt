package com.tambapps.fft4j.fourier.fft_1d

import com.tambapps.fft4j.complex.Complex
import com.tambapps.fft4j.complex.vector.CVector
import com.tambapps.fft4j.fourier.util.Utils.get2Exponent

class CooleyTukeyIterativeFFT: AbstractFastFourierTransform() {

    override val name = "Cooley-Tukey iterative"
    override val description = "Cooley-Tukey algorithm implemented iteratively (input array must have power of 2 sizes)"

    override fun compute(vector: CVector) {
        val n: Int = vector.size

        val bits = get2Exponent(n)
        bitReverseVector(vector, bits)

        var m = 2
        while (m <= n) {
            var i = 0
            while (i < n) {
                for (k in 0 until m / 2) {
                    val evenIndex = i + k
                    val oddIndex = i + k + m / 2
                    val even: Complex = vector[evenIndex]
                    val odd: Complex = vector[oddIndex]
                    val wm: Complex = Complex.expI(-2.0 * Math.PI * k / m.toDouble()) * odd
                    vector[evenIndex] = even + wm
                    vector[oddIndex] = even - wm
                }
                i += m
            }
            m = m shl 1
        }
    }

    private fun bitReverseVector(buffer: CVector, bits: Int) {
        for (j in 1 until buffer.size / 2) {
            val swapPos: Int = bitReversedIndex(j, bits)
            val temp: Complex = buffer[j]
            buffer[j] = buffer[swapPos]
            buffer[swapPos] = temp
        }
    }

    private fun bitReversedIndex(n: Int, bits: Int): Int {
        var n = n
        var reversedN = n
        var count = bits - 1
        n = n shr 1
        while (n > 0) {
            reversedN = reversedN shl 1 or (n and 1)
            count--
            n = n shr 1
        }
        return reversedN shl count and (1 shl bits) - 1
    }
}