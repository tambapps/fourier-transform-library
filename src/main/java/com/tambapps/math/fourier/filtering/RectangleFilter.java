package com.tambapps.math.fourier.filtering;

import com.tambapps.math.complex.Complex;

/**
 * A rectangle filter. Let in/out, values that are inside a centered rectangle
 */
class RectangleFilter extends AbstractFilter {

  private final int width;
  private final int height;
  private final boolean inverted;

  RectangleFilter(int width, int height, boolean inverted) {
    this.width = width;
    this.height = height;
    this.inverted = inverted;
  }

  @Override
  Complex apply(Complex c, int i, int j, int M, int N) {
    if (i < width || i >= M - width || j < height || j >= N - height) {
      return inverted ? c : Complex.ZERO;
    }
    return inverted ? Complex.ZERO : c;
  }

}
