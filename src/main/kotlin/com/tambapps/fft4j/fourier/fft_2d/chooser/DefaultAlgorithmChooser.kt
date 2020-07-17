package com.tambapps.fft4j.fourier.fft_2d.chooser

import com.tambapps.fft4j.fourier.fft_1d.FFTAlgorithm

class DefaultAlgorithmChooser(private val algorithm: FFTAlgorithm): AlgorithmChooser {

    override fun getAlgorithm(M: Int, N: Int): FFTAlgorithm {
        return algorithm
    }
}
