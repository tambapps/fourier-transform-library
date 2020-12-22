package com.tambapps.fft4j.complex.vector;

import com.tambapps.fft4j.complex.Complex;

/**
 * An immutable CVector
 */
public final class ImmutableCVector extends ArrayCVector {

  ImmutableCVector(Complex[] values) {
    super(values);
  }

  @Override
  public void setAt(int i, Complex value) {
    throw new UnsupportedOperationException("Cannot modify immutable vector");
  }

  public static CVector of(Complex... values) {
    return new ImmutableCVector(values);
  }

}
