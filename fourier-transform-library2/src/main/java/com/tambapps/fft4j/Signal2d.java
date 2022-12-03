package com.tambapps.fft4j;

public class Signal2d extends Signal {
  public Signal2d(int length) {
    super(length);
  }

  public Signal2d(double[] re) {
    super(re);
  }

  public Signal2d(double[] re, double[] im) {
    super(re, im);
  }
}
