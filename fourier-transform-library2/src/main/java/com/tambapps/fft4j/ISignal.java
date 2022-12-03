package com.tambapps.fft4j;

import lombok.Getter;

public interface ISignal {

  double getReAt(int i);

  double getImAt(int i);

  void setReAt(int i, double value);

  void setImAt(int i, double value);

  void copyInto(ISignal signal);

  int getLength();
}
