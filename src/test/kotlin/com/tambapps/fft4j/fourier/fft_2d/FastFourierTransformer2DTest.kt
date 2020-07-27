package com.tambapps.fft4j.fourier.fft_2d

import com.tambapps.fft4j.complex.Complex
import com.tambapps.fft4j.complex.array2d.CArray2D
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.*

class FastFourierTransformer2DTest {

    companion object {
        private const val LIMIT = 1024.0
        private const val N = 256
    }

    private val transformer2D = FastFourierTransformer2D()

    @Test
    fun testSuccess() {
        val random = Random()
        val array2D = CArray2D(N, N)
        for (i in 0 until N * N) {
            array2D[i] = Complex.of(random.nextDouble() % LIMIT)
        }
        val originArray: CArray2D = array2D.copy()
        // should run without exception
        transformer2D.transform(array2D)
        transformer2D.inverse(array2D)
        for (i in 0 until N * N) {
            assertEquals("Should be equal", originArray[i], array2D[i])
        }
    }
}