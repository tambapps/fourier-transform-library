package com.tambapps.fft4j;

import com.tambapps.fft4j.task.FourierInverseTask;
import com.tambapps.fft4j.task.FourierTransformTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Class allowing to perform 2d FFT on 2D Signals. It uses an ExecutorService to speed-up the process
 */
public class FastFourier2d {

  private final int slicesCount;
  private final ExecutorService executorService;


  public FastFourier2d() {
    this(Runtime.getRuntime().availableProcessors() + 1);
  }

  public FastFourier2d(int slicesCount) {
    this(slicesCount, Executors.newFixedThreadPool(slicesCount));

  }

  public FastFourier2d(int slicesCount, ExecutorService executorService) {
    this.slicesCount = slicesCount;
    this.executorService = executorService;
  }

  /**
   * Perform a 2d fourier transform on the provided signal
   *
   * @param signal2d a 2d signal
   */
  public void transform(Signal2d signal2d) {
    FastFourier fastFourier = FastFourierUtils.is2Power(signal2d.getM()) && FastFourierUtils.is2Power(signal2d.getN()) ?
        FastFouriers.RECURSIVE_COOLEY_TUKEY :
        FastFouriers.BASIC;
    transform(signal2d, fastFourier);
  }

  /**
   * Perform a 2d fourier transform on the provided signal, using the provided fourier algorithm
   *
   * @param signal2d    the signal
   * @param fastFourier the fourier algorithm to use
   */
  public void transform(Signal2d signal2d, FastFourier fastFourier) {
    List<Future<?>> futures = new ArrayList<>();
    int count = signal2d.getM();
    int threadHandledTasksCount = (int) (((float) count) * ((float) slicesCount - 1) / ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierTransformTask(signal2d.getRow(i), fastFourier)));
    }

    // also make the main thread work
    for (int i = threadHandledTasksCount; i < count; i++) {
      new FourierTransformTask(signal2d.getRow(i), fastFourier).run();
    }

    wait(futures);

    count = signal2d.getN();
    threadHandledTasksCount = (int) (((float) count) * ((float) slicesCount - 1) / ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierTransformTask(signal2d.getColumn(i), fastFourier)));
    }

    // also make the main thread work
    for (int i = threadHandledTasksCount; i < count; i++) {
      new FourierTransformTask(signal2d.getColumn(i), fastFourier).run();
    }
    wait(futures);
  }

  /**
   * Perform a 2d fourier inverse on the provided signal
   *
   * @param signal2d a 2d signal
   */
  public void inverse(Signal2d signal2d) {
    FastFourier fastFourier = FastFourierUtils.is2Power(signal2d.getM()) && FastFourierUtils.is2Power(signal2d.getN()) ?
        FastFouriers.RECURSIVE_COOLEY_TUKEY :
        FastFouriers.BASIC;
    inverse(signal2d, fastFourier);
  }

  /**
   * Perform a 2d fourier inverse on the provided signal, using the provided fourier algorithm
   *
   * @param signal2d    the signal
   * @param fastFourier the fourier algorithm to use
   */
  public void inverse(Signal2d signal2d, FastFourier fastFourier) {
    List<Future<?>> futures = new ArrayList<>();
    int count = signal2d.getM();
    int threadHandledTasksCount = (int) (((float) count) * ((float) slicesCount - 1) / ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierInverseTask(signal2d.getRow(i), fastFourier)));
    }

    // also make the main thread work
    for (int i = threadHandledTasksCount; i < count; i++) {
      new FourierInverseTask(signal2d.getRow(i), fastFourier).run();
    }

    wait(futures);

    count = signal2d.getN();
    threadHandledTasksCount = (int) (((float) count) * ((float) slicesCount - 1) / ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierInverseTask(signal2d.getColumn(i), fastFourier)));
    }

    // also make the main thread work
    for (int i = threadHandledTasksCount; i < count; i++) {
      new FourierInverseTask(signal2d.getColumn(i), fastFourier).run();
    }
    wait(futures);
  }


  /**
   * Shutdown the executor service
   */
  public void shutdown() {
    executorService.shutdown();
  }

  private void wait(List<Future<?>> futures) {
    for (Future<?> future : futures) {
      try {
        future.get();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        return;
      } catch (ExecutionException e) {
        throw new RuntimeException(e);
      }
    }
    futures.clear();
  }
}
