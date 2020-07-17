package com.tambapps.fft4j.fourier.filtering

/**
 * Util class to get pre-made filters
 */
object Filters {
    /**
     * Returns a new rectangle filter with the given width and height
     *
     * @param width    width of filter
     * @param height   height of filter
     * @param filterIn whether to filter in or out the rectangle
     * @return the rectangle filter
     */
    fun rectangle(width: Int, height: Int, filterIn: Boolean): Filter {
        return RectangleFilter(width, height, filterIn)
    }

    /**
     * Returns a new threshold filter
     *
     * @param max         the threshold value
     * @param filterLower whether to filter value below or above the threshold
     * @return the threshold filter
     */
    fun threshold(max: Double, filterLower: Boolean): Filter {
        return ThresholdFilter(max, filterLower)
    }

    /**
     * Returns a new circle filter with the given width and height
     *
     * @param radius   width of filter
     * @param filterIn whether to filter in or out the rectangle
     * @return the rectangle filter
     */
    fun circle(radius: Int, filterIn: Boolean): Filter {
        return CircleFilter(radius, filterIn)
    }
}