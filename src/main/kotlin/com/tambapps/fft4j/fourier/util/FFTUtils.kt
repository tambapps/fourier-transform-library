package com.tambapps.fft4j.fourier.util

import com.tambapps.fft4j.complex.Complex
import com.tambapps.fft4j.complex.array2d.CArray2D

object FFTUtils {

    /**
     * Changes the center of the Fourier Transform
     *
     * @param array the FT
     */
    fun changeCenter(array: CArray2D) {
        for (i in 0 until array.m / 2) {
            for (j in 0 until array.n / 2) {
                swap(array, i, j, i + array.m / 2, j + array.n / 2)
                swap(array, i, j + array.n / 2, i + array.m / 2, j)
            }
        }
    }

    private fun swap(array: CArray2D, i1: Int, j1: Int, i2: Int, j2: Int) {
        val temp: Complex = array[i1, j1]
        array[i1, j1] = array[i2, j2]
        array[i2, j2] = temp
    }

    /**
     * Makes a padded copy of the given array
     *
     * @param array   an array of values
     * @param padding the padding wanted
     * @return the padded copy of the given array
     */
    fun paddedCopy(array: CArray2D, padding: Padding): CArray2D? {
        return if (padding == Padding.ZERO) {
            array.copy()
        } else paddedCopy(array, padding.left, padding.right, padding.top,
                padding.end)
    }

    /**
     * Makes a padded copy of the given array
     *
     * @param array        an array of values
     * @param paddingLeft  the left padding
     * @param paddingRight the right padding
     * @param paddingTop   the top padding
     * @param paddingEnd   the bottom padding
     * @return the padded copy of the given array
     */
    fun paddedCopy(array: CArray2D, paddingLeft: Int, paddingRight: Int,
                   paddingTop: Int, paddingEnd: Int): CArray2D? {
        val copy = CArray2D(array.m + paddingTop + paddingEnd,
                array.n + paddingLeft + paddingRight)
        for (i in 0 until copy.m) {
            for (j in 0 until copy.n) {
                if (i < paddingEnd || i - paddingEnd >= array.m || j < paddingLeft || j - paddingLeft >= array.n) {
                    copy.set(i, j, Complex.ZERO)
                } else {
                    copy.set(i, j, array.get(i - paddingEnd, j - paddingLeft))
                }
            }
        }
        return copy
    }

    /**
     * Makes an unpadded copy of the given array
     *
     * @param array   an array of values
     * @param padding the padding wanted
     * @return the unpadded copy of the given array
     */
    fun unpaddedCopy(array: CArray2D, padding: Padding): CArray2D? {
        return if (padding == Padding.ZERO) {
            array.copy()
        } else unpaddedCopy(array, padding.left, padding.right, padding.top,
                padding.end)
    }

    /**
     * Makes an unpadded copy of the given array
     *
     * @param array        an array of values
     * @param paddingLeft  the left padding
     * @param paddingRight the right padding
     * @param paddingTop   the top padding
     * @param paddingEnd   the bottom padding
     * @return the unpadded copy of the given array
     */
    fun unpaddedCopy(array: CArray2D, paddingLeft: Int, paddingRight: Int,
                     paddingTop: Int, paddingEnd: Int): CArray2D? {
        val copy = CArray2D(array.m - paddingTop - paddingEnd,
                array.n - paddingLeft - paddingRight)
        for (i in 0 until copy.m) {
            for (j in 0 until copy.n) {
                copy[i, j] = array[i + paddingEnd, j + paddingLeft]
            }
        }
        return copy
    }

}