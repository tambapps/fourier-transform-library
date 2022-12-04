package com.tambapps.fft4j;

/**
 * Util class for handling 2-exponents
 */
public final class FastFourierUtils {

  private FastFourierUtils() {
  }

  /**
   * Returns the exponent of a power of two
   *
   * @param x the power of two
   * @return the corresponding 2-exponent
   * @throws IllegalArgumentException if the argument is not a power of two
   */
  public static int get2Exponent(int x) {
    int power = 0;
    int number = 1;
    while (number < x) {
      power++;
      number *= 2;
    }
    if (number != x) {
      throw new IllegalArgumentException("Size is not a power of 2");
    }
    return power;
  }

  public static void swap(double[] re, double[] im, int i1, int i2) {
    double tempRe = re[i1];
    double tempIm = im[i1];

    re[i1] = re[i2];
    im[i1] = im[i2];

    re[i2] = tempRe;
    im[i2] = tempIm;
  }


  /**
   * Returns whether a number is a power of two
   *
   * @param x a number
   * @return whether it is a power of two
   */
  public static boolean is2Power(int x) {
    try {
      get2Exponent(x);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  public static void checkSizes(double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm) {
    if (inputRe.length != inputIm.length || inputRe.length != outputRe.length || inputRe.length != outputIm.length) {
      throw new IllegalArgumentException("All arrays should have same length");
    }
  }

}
