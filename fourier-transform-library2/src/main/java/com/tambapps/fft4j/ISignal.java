package com.tambapps.fft4j;

import lombok.Getter;

public interface ISignal {

  double getReAt(int i);

  double getImAt(int i);

  void setReAt(int i, double value);

  default void setAt(int i, double re, double im) {
    setReAt(i, re);
    setImAt(i, im);
  }

  void setImAt(int i, double value);

  default void setFrom(double[] re, double[] im) {
    for (int i = 0; i < getLength(); i++) {
      setReAt(i, re[i]);
      setImAt(i, im[i]);
    }
  }
  default void copyInto(double[] re, double[] im) {
    for (int i = 0; i < getLength(); i++) {
      re[i] = getReAt(i);
      im[i] = getImAt(i);
    }
  }

  default void copyInto(ISignal signal) {
    for (int i = 0; i < getLength(); i++) {
      signal.setReAt(i, getReAt(i));
      signal.setImAt(i, getImAt(i));
    }
  }

  int getLength();
}
