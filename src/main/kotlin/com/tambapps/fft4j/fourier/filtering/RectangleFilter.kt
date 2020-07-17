package com.tambapps.fft4j.fourier.filtering

import com.tambapps.fft4j.complex.Complex

/**
 * A rectangle filter. Filters values that are in/out a centered rectangle
 */
class RectangleFilter
/**
 * Creates a rectangle filter with the given width and height
 *
 * @param width    the filter's width
 * @param height   the filter's height
 * @param filterIn whether to filter in or out the rectangle
 */(private val width: Int, private val height: Int, private val filterIn: Boolean) : AbstractFilter() {

    override fun apply(c: Complex, i: Int, j: Int, M: Int, N: Int): Complex {
        if (i < width || i >= M - width || j < height || j >= N - height) {
            return if (filterIn) c else Complex.ZERO
        }
        return if (filterIn) Complex.ZERO else c
    }

}