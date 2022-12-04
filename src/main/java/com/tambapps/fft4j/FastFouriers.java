package com.tambapps.fft4j;

import com.tambapps.fft4j.algorithm.BasicFastFourier;
import com.tambapps.fft4j.algorithm.BestFastFourier;
import com.tambapps.fft4j.algorithm.IterativeCooleyTukeyFastFourier;
import com.tambapps.fft4j.algorithm.RecursiveCooleyTukeyFastFourier;

/**
 * Handy class to perform fourier transform/inverse with different algorithm
 */
public final class FastFouriers {

  private FastFouriers() {
  }

  public static final FastFourier BEST = new BestFastFourier();
  public static final FastFourier BASIC = new BasicFastFourier();
  public static final FastFourier ITERATIVE_COOLEY_TUKEY = new IterativeCooleyTukeyFastFourier();
  public static final FastFourier RECURSIVE_COOLEY_TUKEY = new RecursiveCooleyTukeyFastFourier();

}
