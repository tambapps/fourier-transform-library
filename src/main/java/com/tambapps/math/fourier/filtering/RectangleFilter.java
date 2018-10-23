package com.tambapps.math.fourier.filtering;

import com.tambapps.math.complex.Complex;

/**
 * A rectangle filter. Filters values that are in/out a centered rectangle
 */
class RectangleFilter extends AbstractFilter {

  private final int width;
  private final int height;
  private final boolean filterIn;

  /**
   * Creates a rectangle filter with the given width and height
   * @param width the filter's width
   * @param height the filter's height
   * @param filterIn whether to filter in or out the rectangle
   */
  RectangleFilter(int width, int height, boolean filterIn) {
    this.width = width;
    this.height = height;
    this.filterIn = filterIn;
  }

  @Override
  Complex apply(Complex c, int i, int j, int M, int N) {
    if (i < width || i >= M - width || j < height || j >= N - height) {
      return filterIn ? c : Complex.ZERO;
    }
    return filterIn ? Complex.ZERO : c;
  }

}
