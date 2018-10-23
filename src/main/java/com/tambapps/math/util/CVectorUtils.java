package com.tambapps.math.util;

import com.tambapps.math.complex.Complex;

public class CVectorUtils {

  public static CVector of(Complex... values) {
    return new ImmutableCVector(values);
  }

  public static void copy(CVector src, CVector dst) {
    if (src.getSize() != dst.getSize()) {
      throw new IllegalArgumentException("Both vectors should have the same size");
    }
    for (int i = 0; i < src.getSize(); i++) {
      dst.setAt(i, src.getAt(i));
    }
  }

}
