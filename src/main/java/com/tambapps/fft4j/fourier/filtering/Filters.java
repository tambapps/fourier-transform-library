package com.tambapps.fft4j.fourier.filtering;

/**
 * Util class to get pre-made filters
 */
public final class Filters {

  private Filters() {
  }

  public static Filter rectangle(int width, int height, boolean filterIn) {
    return new RectangleFilter(width, height, filterIn);
  }

  public static Filter threshold(double max, boolean filterLower) {
    return new ThresholdFilter(max, filterLower);
  }

  public static Filter circle(int radius, boolean filterIn) {
    return new CircleFilter(radius, filterIn);
  }
}
