package com.tambapps.fft4j.fourier.filtering

import com.tambapps.fft4j.complex.array2d.CArray2D

/**
 * A filter of a Fourier Transform
 */
interface Filter {
    /**
     * Applies the filter on the given array
     *
     * @param array the array to filter
     */
    fun apply(array: CArray2D)

    /**
     * same as apply
     *
     * @param array the array to filter
     */
    operator fun invoke(array: CArray2D)

    /**
     * Applies the filter on a copied array and returns it
     *
     * @param array the array to filter
     * @return the filtered array
     */
    fun applied(array: CArray2D): CArray2D
}
