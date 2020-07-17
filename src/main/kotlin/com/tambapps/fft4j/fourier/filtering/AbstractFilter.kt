package com.tambapps.fft4j.fourier.filtering

import com.tambapps.fft4j.complex.Complex
import com.tambapps.fft4j.complex.array2d.CArray2D

/**
 * An abstract filter. Useful to extends this class when creating a new filter implementation
 */
abstract class AbstractFilter : Filter {

    override fun apply(array: CArray2D) {
        apply(array, array)
    }

    override fun invoke(array: CArray2D) {
        apply(array)
    }

    private fun apply(input: CArray2D, output: CArray2D) {
        val M: Int = input.m
        val N: Int = input.n
        for (i in 0 until M) {
            for (j in 0 until N) {
                output[i, j] = apply(input[i, j], i, j, M, N)
            }
        }
    }

    override fun applied(array: CArray2D): CArray2D {
        val result = CArray2D(array.m, array.n)
        apply(array, result)
        return result
    }

    abstract fun apply(c: Complex, i: Int, j: Int, M: Int, N: Int): Complex
}
