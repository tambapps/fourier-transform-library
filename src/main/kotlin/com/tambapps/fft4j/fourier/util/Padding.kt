package com.tambapps.fft4j.fourier.util

data class Padding(val left: Int, val top: Int, val right: Int, val end: Int) {
    companion object {
        val ZERO = Padding(0, 0, 0, 0)
    }
}