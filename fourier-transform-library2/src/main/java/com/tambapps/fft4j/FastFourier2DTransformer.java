package com.tambapps.fft4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FastFourier2DTransformer {

  private final int slicesCount;
  private final ExecutorService executorService;

  private FastFourierTransform fastFourierTransform = FastFourier.BEST;

  public FastFourier2DTransformer() {
    this(Runtime.getRuntime().availableProcessors() + 1);
  }

  public FastFourier2DTransformer(int slicesCount) {
    this(slicesCount, Executors.newFixedThreadPool(slicesCount));

  }
  public FastFourier2DTransformer(int slicesCount, ExecutorService executorService) {
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
    int count = output.getM();
    /* TODO
    int threadHandledTasksCount = (int) (((float)count) * ((float) slicesCount - 1)/ ((float) nbThreads));
    for (int i = 0; i < threadHandledTasksCount; i++) {
      futures.add(executorService.submit(taskCreator.apply(vectorExtractor.apply(i) ,algorithm)));
    }

     */
  }


}
