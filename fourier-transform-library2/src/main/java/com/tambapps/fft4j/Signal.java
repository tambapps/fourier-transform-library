package com.tambapps.fft4j;

import lombok.Getter;

public class Signal {

  @Getter
  final double[] re;
  @Getter
  final double[] im;

  public Signal(int length) {
    this(new double[length]);
  }
  public Signal(double[] re) {
    this(re, new double[re.length]);
  }

  public Signal(double[] re, double[] im) {
    this.re = re;
    this.im = im;
    if (re.length != im.length) {
      throw new IllegalArgumentException("Real and imaginary parts should have same length");
    }
  }

  public double getReAt(int i) {
    return re[i];
  }

  public double getImAt(int i) {
    return im[i];
  }

  public void setReAt(int i, double value) {
    re[i] = value;
  }

  public void setImAt(int i, double value) {
    im[i] = value;
  }

  public void copyInto(Signal signal) {
    if (getLength() != signal.getLength()) {
      throw new IllegalArgumentException("Cannot copy signal in another with different length");
    }
    System.arraycopy(re, 0, signal.re, 0, getLength());
    System.arraycopy(im, 0, signal.im, 0, getLength());
  }

  public int getLength() {
    return re.length;
  }
}
