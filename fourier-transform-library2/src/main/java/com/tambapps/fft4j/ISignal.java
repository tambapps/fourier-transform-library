package com.tambapps.fft4j;

import lombok.Getter;

public interface ISignal {

  double getReAt(int i);

  double getImAt(int i);

  void setReAt(int i, double value);

  void setImAt(int i, double value);

  default void copyInto(ISignal signal) {
    for (int i = 0; i < getLength(); i++) {
      signal.setReAt(i, getReAt(i));
      signal.setImAt(i, getImAt(i));
    }
  }

  int getLength();
}
