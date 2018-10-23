package com.tambapps.math.fourier.filtering;

import com.tambapps.math.complex.Complex;

/**
 * A circle filter. Filters values that are in/out a centered circle
 */
class CircleFilter extends AbstractFilter {

  private final int radius2;
  private final boolean filterIn;
  private int cM;
  private int cN;

  /**
   * Creates a circle filter with the given radius.
   *
   * @param radius   the radius of the filter
   * @param filterIn whether to filter in or out the circle
   */
  CircleFilter(int radius, boolean filterIn) {
    this.radius2 = pow2(radius);
    this.filterIn = filterIn;
  }

  @Override
  void before(int M, int N) {
    cM = M / 2;
    cN = N / 2;
  }

  @Override
  Complex apply(Complex c, int i, int j, int M, int N) {
    int distance2 = pow2(i - cM) + pow2(j - cN);
    if (distance2 < radius2) {
      return filterIn ? c : Complex.ZERO;
    }
    return filterIn ? Complex.ZERO : c;
  }

  private int pow2(int i) {
    return i * i;
  }
}
