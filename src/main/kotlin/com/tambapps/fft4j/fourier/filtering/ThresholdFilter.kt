package com.tambapps.fft4j.fourier.filtering

import com.tambapps.fft4j.complex.Complex

/**
 * A threshold filter. Filters values that are greater/lower than a threshold
 */
class ThresholdFilter
/**
 * Creates a threshold filter
 *
 * @param threshold   the threshold
 * @param filterLower whether to filter values lower or greater than the threshold
 */(private val threshold: Double, private val filterLower: Boolean) : AbstractFilter() {

    override fun apply(c: Complex, i: Int, j: Int, M: Int, N: Int): Complex {
        return if (filterLower) {
            if (c.abs() < threshold) c else Complex.ZERO
        } else {
            if (c.abs() > threshold) c else Complex.ZERO
        }
    }

}