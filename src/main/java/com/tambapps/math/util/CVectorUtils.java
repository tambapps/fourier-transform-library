package com.tambapps.math.util;

import com.tambapps.math.complex.Complex;

/**
 * Util class for creating vectors
 */
public class CVectorUtils {

  /**
   * Creates an immutable vector with the given complex numbers
   *
   * @param values values to put in the vector
   * @return an immutable vector
   */
  public static CVector of(Complex... values) {
    return new ImmutableCVector(values);
  }

  /**
   * Copy a vector onto another
   *
   * @param src the source vector
   * @param dst the destination vector
   */
  public static void copy(CVector src, CVector dst) {
    if (src.getSize() != dst.getSize()) {
      throw new IllegalArgumentException("Both vectors should have the same size");
    }
    for (int i = 0; i < src.getSize(); i++) {
      dst.setAt(i, src.getAt(i));
    }
  }

}
