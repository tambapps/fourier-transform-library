package com.tambapps.fft4j.complex.vector;

import com.tambapps.fft4j.complex.Complex;

/**
 * Complex vector where vectors are stored in an array
 */
public class ArrayCVector extends AbstractCVector {

  private final Complex[] values;

  /**
   * Constructs a vector with the given size
   *
   * @param size size of vector
   */
  public ArrayCVector(int size) {
    values = new Complex[size];
  }

  /**
   * Constructs a vector with the given values
   *
   * @param values values of the vector
   */
  ArrayCVector(Complex[] values) {
    this.values = values;
  }

  @Override
  public Complex getAt(int i) {
    return values[checkedIndex(i)];
  }

  @Override
  public void setAt(int i, Complex value) {
    values[checkedIndex(i)] = value;
  }

  @Override
  public int getSize() {
    return values.length;
  }

}
