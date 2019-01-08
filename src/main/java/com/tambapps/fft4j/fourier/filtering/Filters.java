package com.tambapps.fft4j.fourier.filtering;

/**
 * Util class to get pre-made filters
 */
public final class Filters {

  private Filters() {
  }

  /**
   * Returns a new rectangle filter with the given width and height
   *
   * @param width    width of filter
   * @param height   height of filter
   * @param filterIn whether to filter in or out the rectangle
   * @return the rectangle filter
   */
  public static Filter rectangle(int width, int height, boolean filterIn) {
    return new RectangleFilter(width, height, filterIn);
  }

  /**
   * Returns a new threshold filter
   *
   * @param max         the threshold value
   * @param filterLower whether to filter value below or above the threshold
   * @return the threshold filter
   */
  public static Filter threshold(double max, boolean filterLower) {
    return new ThresholdFilter(max, filterLower);
  }

  /**
   * Returns a new circle filter with the given width and height
   *
   * @param radius   width of filter
   * @param filterIn whether to filter in or out the rectangle
   * @return the rectangle filter
   */
  public static Filter circle(int radius, boolean filterIn) {
    return new CircleFilter(radius, filterIn);
  }

}
