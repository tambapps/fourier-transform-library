package com.tambapps.fft4j;

import com.tambapps.fft4j.task.FourierInverseTask;
import com.tambapps.fft4j.task.FourierTransformTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

  public void transform(Signal2d signal2d) {
    FastFourier fastFourier = FastFourierUtils.is2Power(signal2d.getM()) && FastFourierUtils.is2Power(signal2d.getN()) ?
        FastFouriers.RECURSIVE_COOLEY_TUKEY :
        FastFouriers.BASIC;
    transform(signal2d, fastFourier);
  }
  public void transform(Signal2d signal2d, FastFourier fastFourier) {
    List<Future<?>> futures = new ArrayList<>();
    int count = signal2d.getM();
    int threadHandledTasksCount = (int) (((float)count) * ((float) slicesCount - 1)/ ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierTransformTask(signal2d.getRow(i), fastFourier)));
    }

    // also make the main thread work
    for (int i = threadHandledTasksCount; i < count; i++) {
      new FourierTransformTask(signal2d.getRow(i), fastFourier).run();
    }

    wait(futures);

    count = signal2d.getN();
    threadHandledTasksCount = (int) (((float)count) * ((float) slicesCount - 1)/ ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierTransformTask(signal2d.getColumn(i), fastFourier)));
    }

    // also make the main thread work
    for (int i = threadHandledTasksCount; i < count; i++) {
      new FourierTransformTask(signal2d.getColumn(i), fastFourier).run();
    }
    wait(futures);
  }

  public void inverse(Signal2d signal2d) {
    FastFourier fastFourier = FastFourierUtils.is2Power(signal2d.getM()) && FastFourierUtils.is2Power(signal2d.getN()) ?
        FastFouriers.RECURSIVE_COOLEY_TUKEY :
        FastFouriers.BASIC;
    inverse(signal2d, fastFourier);
  }

  public void inverse(Signal2d signal2d, FastFourier fastFourier) {
    List<Future<?>> futures = new ArrayList<>();
    int count = signal2d.getM();
    int threadHandledTasksCount = (int) (((float)count) * ((float) slicesCount - 1)/ ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierInverseTask(signal2d.getRow(i), fastFourier)));
    }

    // also make the main thread work
    for (int i = threadHandledTasksCount; i < count; i++) {
      new FourierInverseTask(signal2d.getRow(i), fastFourier).run();
    }

    wait(futures);

    count = signal2d.getN();
    threadHandledTasksCount = (int) (((float)count) * ((float) slicesCount - 1)/ ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierInverseTask(signal2d.getColumn(i), fastFourier)));
    }

    // also make the main thread work
    for (int i = threadHandledTasksCount; i < count; i++) {
      new FourierInverseTask(signal2d.getColumn(i), fastFourier).run();
    }
    wait(futures);
  }


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
