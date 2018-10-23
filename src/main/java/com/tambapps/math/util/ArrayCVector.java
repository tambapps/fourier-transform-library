package com.tambapps.math.util;

import com.tambapps.math.complex.Complex;

public class ArrayCVector extends AbstractCVector {

  private final Complex[] values;

  public ArrayCVector(int size) {
    values = new Complex[size];
  }

  ArrayCVector(Complex[] values) {
    this.values = values;
  }

  @Override
  public Complex getAt(int i) {
    checkIndex(i);
    return values[i];
  }

  @Override
  public void setAt(int i, Complex value) {
    checkIndex(i);
    values[i] = value;
  }

  @Override
  public int getSize() {
    return values.length;
  }

}
