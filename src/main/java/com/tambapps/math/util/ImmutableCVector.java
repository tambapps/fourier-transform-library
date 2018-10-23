package com.tambapps.math.util;

import com.tambapps.math.complex.Complex;

class ImmutableCVector extends ArrayCVector {

  ImmutableCVector(Complex[] values) {
    super(values);
  }

  @Override
  public void setAt(int i, Complex value) {
    throw new UnsupportedOperationException("Cannot modify immutable vector");
  }

}
