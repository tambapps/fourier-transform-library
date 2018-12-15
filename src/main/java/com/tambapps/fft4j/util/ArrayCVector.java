package com.tambapps.fft4j.util;

import com.tambapps.fft4j.complex.Complex;

/**
 * Complex vector where vectors are stored in an array
 */
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
