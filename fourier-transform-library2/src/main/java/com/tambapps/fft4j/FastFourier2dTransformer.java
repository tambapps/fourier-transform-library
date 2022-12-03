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

  public Signal2d transform(Signal2d input) {
    Signal2d output = new Signal2d(input.getM(), input.getN());
    if (input.getN() != output.getN() || input.getM() != output.getM()) {
      throw new IllegalArgumentException("Input and output should have same dimensions");
    }
    transform(input, output);
    return output;
  }

  public void transform(Signal2d input, Signal2d output) {
    List<Future<?>> futures = new ArrayList<>();
    int threadHandledTasksCount = (int) (((float)output.getM()) * ((float) slicesCount - 1)/ ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierTransformTask(input.getRow(i), fastFourierTransform)));
    }

    wait(futures);

    threadHandledTasksCount = (int) (((float)output.getN()) * ((float) slicesCount - 1)/ ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierTransformTask(input.getColumn(i), fastFourierTransform)));
    }

    wait(futures);
  }

  public Signal2d inverse(Signal2d input) {
    Signal2d output = new Signal2d(input.getM(), input.getN());
    if (input.getN() != output.getN() || input.getM() != output.getM()) {
      throw new IllegalArgumentException("Input and output should have same dimensions");
    }
    inverse(input, output);
    return output;
  }

  public void inverse(Signal2d input, Signal2d output) {
    List<Future<?>> futures = new ArrayList<>();
    int threadHandledTasksCount = (int) (((float)output.getM()) * ((float) slicesCount - 1)/ ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierInverseTask(input.getRow(i), fastFourierTransform)));
    }

    wait(futures);

    threadHandledTasksCount = (int) (((float)output.getN()) * ((float) slicesCount - 1)/ ((float) slicesCount));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(new FourierInverseTask(input.getColumn(i), fastFourierTransform)));
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
  }
}
