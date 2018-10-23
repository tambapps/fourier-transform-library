package com.tambapps.math.fourier.util;

/**
 * Util class for handling 2-exponents
 */
public final class Utils {

  private Utils() {
  }

  private static Integer doGet2Exponent(int x) {
    int power = 0;
    int number = 1;
    while (number < x) {
      power++;
      number *= 2;
    }
    return number == x ? power : null;
  }

  /**
   * Returns the exponent of a power of two
   * @param x the power of two
   * @return the corresponding 2-exponent
   * @throws IllegalArgumentException if the argument is not a power of two
   */
  public static int get2Exponent(int x) throws IllegalArgumentException {
    Integer pow = doGet2Exponent(x);
    if (pow == null) {
      throw new IllegalArgumentException("This number isn't a power of two");
    }
    return pow;
  }

  /**
   * Returns whether a number is a power of two
   * @param x a number
   * @return whether it is a power of two
   */
  public static boolean is2Power(int x) {
    return doGet2Exponent(x) != null;
  }

}
