package com.tambapps.fft4j;

import com.tambapps.fft4j.algorithm.BasicFastFourier;
import com.tambapps.fft4j.algorithm.BestFastFourier;
import com.tambapps.fft4j.algorithm.IterativeCooleyTukeyFastFourier;
import com.tambapps.fft4j.algorithm.RecursiveCooleyTukeyFastFourier;

public class FastFourier {

  public static final FastFourierTransform BEST = new BestFastFourier();
  public static final FastFourierTransform BASIC = new BasicFastFourier();
  public static final FastFourierTransform ITERATIVE_COOLEY_TUKEY = new IterativeCooleyTukeyFastFourier();
  public static final FastFourierTransform RECURSIVE_COOLEY_TUKEY = new RecursiveCooleyTukeyFastFourier();

}