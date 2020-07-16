package com.tambapps.fft4j.complex

import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Test

class ComplexTest {

    @Test
    fun addTest() {
        val complex1 = Complex.of(8.0)
        val complex2 = Complex.of(4.0, 1.0)
        assertEquals("Should be equal", Complex.of(12.0, 1.0), complex1.plus(complex2))
    }

    @Test
    fun pureTest() {
        val pr = Complex.of(3423.0)
        val pi = Complex.of(0.0, 234.0)
        Assert.assertTrue("Should be only pure real", pr.isPureReal && !pr.isPureImaginary)
        Assert.assertTrue("Should be only pure imaginary", pi.isPureImaginary && !pi.isPureReal)
    }

    @Test
    fun mulTest() {
        val c1 = Complex.ONE
        val c2 = Complex.of(0.0, 2.0)
        assertEquals("Should be equal", Complex.ZERO, c1 * Complex.ZERO)
        assertEquals("Should be equal", Complex.ZERO, Complex.ZERO * c1)
        assertEquals("Should be equal", Complex.I * 2.0, c1 * c2)
        assertEquals("Should be equal", Complex.I * 2.0, c2 * c1)
    }

}
