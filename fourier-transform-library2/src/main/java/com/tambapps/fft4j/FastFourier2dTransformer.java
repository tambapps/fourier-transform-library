package com.tambapps.fft4j;

import com.tambapps.fft4j.task.FourierInverseTask;
import com.tambapps.fft4j.task.FourierTransformTask;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FastFourier2dTransformer {

  private final int slicesCount;
  private final ExecutorService executorService;

  @Setter
  private FastFourierTransform fastFourierTransform = FastFourier.BEST;

  public FastFourier2dTransformer() {
    this(Runtime.getRuntime().availableProcessors() + 1);
  }

  public FastFourier2dTransformer(int slicesCount) {
    this(slicesCount, Executors.newFixedThreadPool(slicesCount));

  }
  public FastFourier2dTransformer(int slicesCount, ExecutorService executorService) {
    this.slicesCount = slicesCount;
    this.executorService = executorService;
  }

  public void transform(Signal2d signal2d) {
    List<Future<?>> futures = new ArrayList<>();
    int count = signal2d.getM();
    int threadHandledTasksCount = (int) (((float)count) * ((float) slicesCount - 1)/ ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierTransformTask(signal2d.getRow(i), fastFourierTransform)));
    }

    // also make the main thread work
    for (int i = threadHandledTasksCount; i < count; i++) {
      new FourierTransformTask(signal2d.getRow(i), fastFourierTransform).run();
    }

    wait(futures);

    count = signal2d.getN();
    threadHandledTasksCount = (int) (((float)count) * ((float) slicesCount - 1)/ ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierTransformTask(signal2d.getColumn(i), fastFourierTransform)));
    }

    // also make the main thread work
    for (int i = threadHandledTasksCount; i < count; i++) {
      new FourierTransformTask(signal2d.getColumn(i), fastFourierTransform).run();
    }
    wait(futures);
  }

  public void inverse(Signal2d signal2d) {
    List<Future<?>> futures = new ArrayList<>();
    int count = signal2d.getM();
    int threadHandledTasksCount = (int) (((float)count) * ((float) slicesCount - 1)/ ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierInverseTask(signal2d.getRow(i), fastFourierTransform)));
    }

    // also make the main thread work
    for (int i = threadHandledTasksCount; i < count; i++) {
      new FourierInverseTask(signal2d.getRow(i), fastFourierTransform).run();
    }

    wait(futures);

    count = signal2d.getN();
    threadHandledTasksCount = (int) (((float)count) * ((float) slicesCount - 1)/ ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierInverseTask(signal2d.getColumn(i), fastFourierTransform)));
    }

    // also make the main thread work
    for (int i = threadHandledTasksCount; i < count; i++) {
      new FourierInverseTask(signal2d.getColumn(i), fastFourierTransform).run();
    }

    wait(futures);
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
