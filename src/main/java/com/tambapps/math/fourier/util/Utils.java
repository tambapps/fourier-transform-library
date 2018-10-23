package com.tambapps.math.fourier.util;


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

  public static int get2Exponent(int x) {
    Integer pow = doGet2Exponent(x);
    if (pow == null) {
      throw new IllegalArgumentException("This number isn't a power of two");
    }
    return pow;
  }

  public static boolean is2Power(int x) {
    return doGet2Exponent(x) != null;
  }

}
