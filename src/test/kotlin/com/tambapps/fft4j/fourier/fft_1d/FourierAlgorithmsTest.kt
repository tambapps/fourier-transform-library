package com.tambapps.fft4j.fourier.fft_1d

import com.tambapps.fft4j.complex.Complex
import com.tambapps.fft4j.complex.vector.CVector
import com.tambapps.fft4j.complex.vector.ImmutableCVector
import com.tambapps.fft4j.fourier.fft_1d.FourierAlgorithms.inverse
import junit.framework.Assert.assertEquals
import org.junit.Test

class FourierAlgorithmsTest {
    private val input: CVector = ImmutableCVector.of(ONE,
            ONE,
            ONE,
            ONE,
            Complex.ZERO,
            Complex.ZERO,
            Complex.ZERO,
            Complex.ZERO)
    private val expected: CVector = ImmutableCVector.of(Complex(4.0),
            Complex.of(1.0, -2.414214),
            Complex.ZERO,
            Complex.of(1.0, -0.414214),
            Complex.ZERO,
            Complex.of(1.0, 0.414214),
            Complex.ZERO,
            Complex.of(1.0, 2.414214))

    private fun algorithmTest(algorithm: FFTAlgorithm) {
        val result: CVector = input.copy()
        algorithm.compute(result)
        assertEquals("Should be equal", expected, result)
        assertEquals("Should be equal", expected, algorithm.computeCopy(input))
    }

    @Test
    fun basicTest() {
        algorithmTest(FourierAlgorithms.BASIC)
    }

    @Test
    fun recursiveTest() {
        algorithmTest(FourierAlgorithms.CT_RECURSIVE)
    }

    @Test
    fun iterativeTest() {
        algorithmTest(FourierAlgorithms.CT_ITERATIVE)
    }

    @Test
    fun inverseTest() {
        for (algorithm in listOf(FourierAlgorithms.CT_ITERATIVE,
                FourierAlgorithms.BASIC, FourierAlgorithms.CT_RECURSIVE)) {
            val result: CVector = expected.copy()
            inverse(algorithm).compute(result)
            assertEquals("Incorrect Fourier inverse for $algorithm", input, result)
        }
    }

    companion object {
        private val ONE = Complex.of(1.0)
    }
}