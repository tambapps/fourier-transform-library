package com.tambapps.fft4j.fourier.filtering

import com.tambapps.fft4j.complex.Complex
import com.tambapps.fft4j.complex.array2d.CArray2D
import junit.framework.Assert.assertEquals
import org.junit.Test

class FiltersTest {
    @Test
    fun rectangleFilterTest() {
        val N = 10
        val array = CArray2D(N, N)
        for (i in 0 until array.n * array.m) {
            array[i] = Complex.of(i.toDouble())
        }
        Filters.rectangle(1, 1, false).apply(array)
        val expected: CArray2D = array.copy()
        for (i in 0 until N) {
            expected[i, 0] = Complex.ZERO
            expected[0, i] = Complex.ZERO
            expected[i, N - 1] = Complex.ZERO
            expected[N - 1, i] = Complex.ZERO
        }
        assertEquals("Should be equal", expected, array)
    }
}
