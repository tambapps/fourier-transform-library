package com.tambapps.fft4j.util

import kotlin.math.abs

object DoubleTester {

    private var EQUAL_THRESHOLD = 0.000001

    /**
     * Tells whether 2 doubles are equals given a threshold
     *
     * @param d1 the 1st number
     * @param d2 the 2nd number
     * @return weither the 2 numbers are equals
     */
    fun equal(d1: Double, d2: Double): Boolean {
        return abs(d1 - d2) < EQUAL_THRESHOLD
    }

    /**
     * Sets the value of the threshold
     *
     * @param threshold the new value to be set
     */
    fun setThreshold(threshold: Double) {
        EQUAL_THRESHOLD = threshold
    }
}