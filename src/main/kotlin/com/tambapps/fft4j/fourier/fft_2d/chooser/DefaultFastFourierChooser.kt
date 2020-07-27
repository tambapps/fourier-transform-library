package com.tambapps.fft4j.fourier.fft_2d.chooser

import com.tambapps.fft4j.fourier.fft_1d.FastFourierTransform

class DefaultFastFourierChooser(private val fft: FastFourierTransform): FastFourierChooser {

    override fun getFastFourierTransform(M: Int, N: Int): FastFourierTransform {
        return fft
    }
}
