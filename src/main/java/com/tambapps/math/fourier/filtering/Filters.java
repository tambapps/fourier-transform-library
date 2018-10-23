package com.tambapps.math.fourier.filtering;

/**
 * Util class to get pre-made filters
 */
public final class Filters {

  private Filters() {
  }

  public static Filter rectangle(int width, int height, boolean inverted) {
    return new RectangleFilter(width, height, inverted);
  }

  public static Filter threshold(double max) {
    return new ThresholdFilter(max);
  }

  public static Filter circle(int radius, boolean reverted) {
    return new CircleFilter(radius, reverted);
  }
}
