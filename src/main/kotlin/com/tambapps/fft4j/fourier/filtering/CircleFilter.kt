package com.tambapps.fft4j.fourier.filtering

import com.tambapps.fft4j.complex.Complex

/**
 * A circle filter. Filters values that are in/out a centered circle
 *
 * @param radius   the radius of the filter
 * @param filterIn whether to filter in or out the circle
 */
class CircleFilter(radius: Int, filterIn: Boolean) : AbstractFilter() {
    private val radius2: Int
    private val filterIn: Boolean

    init {
        radius2 = pow2(radius)
        this.filterIn = filterIn
    }

    override fun apply(c: Complex, i: Int, j: Int, M: Int, N: Int): Complex {
        val cM = M / 2
        val cN = N / 2
        val distance2 = pow2(i - cM) + pow2(j - cN)
        if (distance2 < radius2) {
            return if (filterIn) c else Complex.ZERO
        }
        return if (filterIn) Complex.ZERO else c
    }

    private fun pow2(i: Int): Int {
        return i * i
    }
}
