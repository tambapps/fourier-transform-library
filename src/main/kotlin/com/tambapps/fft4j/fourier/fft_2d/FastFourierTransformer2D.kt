package com.tambapps.fft4j.fourier.fft_2d

import com.tambapps.fft4j.complex.array2d.CArray2D
import com.tambapps.fft4j.fourier.FourierException
import com.tambapps.fft4j.fourier.fft_2d.chooser.AlgorithmChooser
import com.tambapps.fft4j.fourier.fft_2d.task.FourierInverseColumnTask
import com.tambapps.fft4j.fourier.fft_2d.task.FourierInverseRowTask
import com.tambapps.fft4j.fourier.fft_2d.task.FourierTransformColumnTask
import com.tambapps.fft4j.fourier.fft_2d.task.FourierTransformRowTask
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

class FastFourierTransformer2D(private val executor: ExecutorService, var chooser: AlgorithmChooser) {

    // TODO use kotlin types instead of interface
    interface TaskSupplier {
        fun supply(f: CArray2D, i: Int): Runnable
    }

    @Throws(FourierException::class)
    fun transform(f: CArray2D) {
        val algorithm = chooser.getAlgorithm(f.m, f.n)
        compute(f,
                object : TaskSupplier {
                    override fun supply(f: CArray2D, i: Int): Runnable {
                        return FourierTransformRowTask(algorithm, f, i)
                    }
                },
                object : TaskSupplier {
                    override fun supply(f: CArray2D, i: Int): Runnable {
                        return FourierTransformColumnTask(algorithm, f, i)
                    }
                }
        )
    }

    @Throws(FourierException::class)
    fun inverse(f: CArray2D) {
        val algorithm = chooser.getAlgorithm(f.m, f.n)
        compute(f,
                object : TaskSupplier {
                    override fun supply(f: CArray2D, i: Int): Runnable {
                        return FourierInverseRowTask(algorithm, f, i)
                    }
                },
                object : TaskSupplier {
                    override fun supply(f: CArray2D, i: Int): Runnable {
                        return FourierInverseColumnTask(algorithm, f, i)
                    }
                }
        )
    }

    @Throws(FourierException::class)
    private fun compute(f: CArray2D, rowTaskSupplier: TaskSupplier, columnTaskSupplier: TaskSupplier) {
        val futures = mutableListOf<Future<*>>()
        for (i in 0 until f.m) {
            futures.add(executor.submit(rowTaskSupplier.supply(f, i)))
        }
        waitFutures(futures)
        for (i in 0 until f.n) {
            futures.add(executor.submit(columnTaskSupplier.supply(f, i)))
        }
        waitFutures(futures)
    }

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
