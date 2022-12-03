package com.tambapps.fft4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FastFourier2DTransformer {

  private final int slicesCount;
  private final ExecutorService executorService;

  private FastFourierFunction fastFourierFunction = FastFourier::transform;

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

  public void transform(Signal input) {

  }
}
