package com.tambapps.fft4j;

import com.tambapps.fft4j.algorithm.BasicFastFourier;
import com.tambapps.fft4j.algorithm.BestFastFourier;
import com.tambapps.fft4j.algorithm.IterativeCooleyTukeyFastFourier;
import com.tambapps.fft4j.algorithm.RecursiveCooleyTukeyFastFourier;

public class FastFouriers {

  public static final FastFourier BEST = new BestFastFourier();
  public static final FastFourier BASIC = new BasicFastFourier();
  public static final FastFourier ITERATIVE_COOLEY_TUKEY = new IterativeCooleyTukeyFastFourier();
  public static final FastFourier RECURSIVE_COOLEY_TUKEY = new RecursiveCooleyTukeyFastFourier();

}
