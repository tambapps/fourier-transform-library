package com.tambapps.fft4j.fourier.filtering;

import com.tambapps.fft4j.complex.Complex;

/**
 * A circle filter. Filters values that are in/out a centered circle
 */
public class CircleFilter extends AbstractFilter {

  private final int radius2;
  private final boolean filterIn;

  /**
   * Creates a circle filter with the given radius.
   *
   * @param radius   the radius of the filter
   * @param filterIn whether to filter in or out the circle
   */
  protected CircleFilter(int radius, boolean filterIn) {
    this.radius2 = pow2(radius);
    this.filterIn = filterIn;
  }

  @Override
  Complex apply(Complex c, int i, int j, int M, int N) {
    int cM = M / 2;
    int cN = N / 2;
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
