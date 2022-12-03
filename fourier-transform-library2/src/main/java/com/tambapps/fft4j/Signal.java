package com.tambapps.fft4j;

import lombok.Getter;

import java.util.Arrays;

public class Signal implements ISignal {

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

  @Override
  public double getReAt(int i) {
    return re[i];
  }

  @Override
  public double getImAt(int i) {
    return im[i];
  }

  @Override
  public void setReAt(int i, double value) {
    re[i] = value;
  }

  @Override
  public void setImAt(int i, double value) {
    im[i] = value;
  }

  @Override
  public void copyInto(ISignal s) {
    if (getLength() != s.getLength()) {
      throw new IllegalArgumentException("Cannot copy signal in another with different length");
    }
    if (s instanceof Signal) {
      Signal signal = (Signal) s;
      System.arraycopy(re, 0, signal.re, 0, getLength());
      System.arraycopy(im, 0, signal.im, 0, getLength());
    } else {
      ISignal.super.copyInto(s);
    }
  }

  @Override
  public int getLength() {
    return re.length;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Signal signal = (Signal) o;
    return Arrays.equals(re, signal.re) && Arrays.equals(im, signal.im);
  }

  @Override
  public int hashCode() {
    int result = Arrays.hashCode(re);
    result = 31 * result + Arrays.hashCode(im);
    return result;
  }
}
