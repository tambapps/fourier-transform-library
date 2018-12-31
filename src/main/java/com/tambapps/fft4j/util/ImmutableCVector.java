package com.tambapps.fft4j.util;

import com.tambapps.fft4j.complex.Complex;

class ImmutableCVector extends ArrayCVector {

  ImmutableCVector(Complex[] values) {
    super(values);
  }

  @Override
  public void setAt(int i, Complex value) {
    throw new UnsupportedOperationException("Cannot modify immutable vector");
  }

}