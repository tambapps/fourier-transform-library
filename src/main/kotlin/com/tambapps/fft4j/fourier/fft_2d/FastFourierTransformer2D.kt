package com.tambapps.fft4j.fourier.fft_2d

import com.tambapps.fft4j.complex.array2d.CArray2D
import com.tambapps.fft4j.fourier.FourierException
import com.tambapps.fft4j.fourier.fft_1d.FastFourierTransform
import com.tambapps.fft4j.fourier.fft_1d.FourierAlgorithms
import com.tambapps.fft4j.fourier.fft_2d.chooser.FastFourierChooser
import com.tambapps.fft4j.fourier.fft_2d.task.FourierInverseColumnTask
import com.tambapps.fft4j.fourier.fft_2d.task.FourierInverseRowTask
import com.tambapps.fft4j.fourier.fft_2d.task.FourierTransformColumnTask
import com.tambapps.fft4j.fourier.fft_2d.task.FourierTransformRowTask
import com.tambapps.fft4j.fourier.util.Utils.is2Power
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class FastFourierTransformer2D(private val executor: ExecutorService, var chooser: FastFourierChooser) {

    companion object {
        val DEFAULT_CHOOSER: FastFourierChooser = object : FastFourierChooser {
            override fun getFastFourierTransform(M: Int, N: Int): FastFourierTransform {
                return if (is2Power(M) && is2Power(N)) FourierAlgorithms.CT_RECURSIVE else FourierAlgorithms.BASIC
            }
        }
    }

    constructor(): this(Runtime.getRuntime().availableProcessors() + 1)
    constructor(nbThreads: Int): this(nbThreads, DEFAULT_CHOOSER)
    constructor(nbThreads: Int, chooser: FastFourierChooser): this(Executors.newFixedThreadPool(nbThreads), chooser)

    @Throws(FourierException::class)
    fun transform(f: CArray2D) {
        transform(f, chooser.getFastFourierTransform(f.m, f.n))
    }
    @Throws(FourierException::class)
    fun transform(f: CArray2D, fft: FastFourierTransform) {
        compute(f, {  i -> FourierTransformRowTask(fft, f, i) },
                { i -> FourierTransformColumnTask(fft, f, i) })
    }

    @Throws(FourierException::class)
    fun inverse(f: CArray2D) {
        val fft = chooser.getFastFourierTransform(f.m, f.n)
        compute(f, {  i -> FourierInverseRowTask(fft, f, i) },
                { i -> FourierInverseColumnTask(fft, f, i) })
    }

    @Throws(FourierException::class)
    private fun compute(f: CArray2D, rowTaskSupplier: (Int) -> Runnable, columnTaskSupplier: (Int) -> Runnable) {
        val futures = mutableListOf<Future<*>>()
        for (i in 0 until f.m) {
            futures.add(executor.submit(rowTaskSupplier(i)))
        }
        waitFutures(futures)
        for (i in 0 until f.n) {
            futures.add(executor.submit(columnTaskSupplier(i)))
        }
        waitFutures(futures)
    }

    @Throws(FourierException::class)
    private fun waitFutures(futures: MutableList<Future<*>>) {
        futures.removeIf {
            try {
                it.get()
            } catch (e: InterruptedException) {
                throw FourierException("Error while performing fourier computation", e)
            } catch (e: ExecutionException) {
                throw FourierException("Error while performing fourier computation", e)
            }
            true
        }
    }

}
