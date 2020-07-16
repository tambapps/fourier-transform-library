package com.tambapps.fft4j.fourier.util

object Utils {

    private fun doGet2Exponent(x: Int): Int? {
        var power = 0
        var number = 1
        while (number < x) {
            power++
            number *= 2
        }
        return if (number == x) power else null
    }

    /**
     * Returns the exponent of a power of two
     *
     * @param x the power of two
     * @return the corresponding 2-exponent
     * @throws IllegalArgumentException if the argument is not a power of two
     */
    @Throws(IllegalArgumentException::class)
    fun get2Exponent(x: Int): Int {
        return doGet2Exponent(x)
                ?: throw IllegalArgumentException("This number isn't a power of two")
    }

    /**
     * Returns whether a number is a power of two
     *
     * @param x a number
     * @return whether it is a power of two
     */
    fun is2Power(x: Int): Boolean {
        return doGet2Exponent(x) != null
    }

}