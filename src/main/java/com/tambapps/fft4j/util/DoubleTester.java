package com.tambapps.fft4j.util;

/**
 * Util class to compare doubles
 */
public class DoubleTester {

  private static double EQUAL_THRESHOLD = 0.000001;

  /**
   * Tells whether 2 doubles are equals given a threshold
   *
   * @param d1 the 1st number
   * @param d2 the 2nd number
   * @return weither the 2 numbers are equals
   */
  public static boolean equal(double d1, double d2) {
    return Math.abs(d1 - d2) < EQUAL_THRESHOLD;
  }

  /**
   * Sets the value of the threshold
   *
   * @param threshold the new value to be set
   */
  public static void setThreshold(double threshold) {
    EQUAL_THRESHOLD = threshold;
  }
}
