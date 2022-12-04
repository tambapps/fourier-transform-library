package com.tambapps.fft4j;

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
    if (getLength() != re.length || getLength() != im.length) {
      throw new IllegalArgumentException("Cannot set signal from arrays with different lengths");
    }
    for (int i = 0; i < getLength(); i++) {
      setReAt(i, re[i]);
      setImAt(i, im[i]);
    }
  }
  default void copyInto(double[] re, double[] im) {
    if (getLength() != re.length || getLength() != im.length) {
      throw new IllegalArgumentException("Cannot copy signal in arrays with different lengths");
    }
    for (int i = 0; i < getLength(); i++) {
      re[i] = getReAt(i);
      im[i] = getImAt(i);
    }
  }

  default void copyInto(ISignal signal) {
    if (getLength() != signal.getLength()) {
      throw new IllegalArgumentException("Cannot copy signal in another with different length");
    }
    for (int i = 0; i < getLength(); i++) {
      signal.setReAt(i, getReAt(i));
      signal.setImAt(i, getImAt(i));
    }
  }

  int getLength();
}
