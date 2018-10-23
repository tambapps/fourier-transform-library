package com.tambapps.math.fourier.util;

import com.tambapps.math.array_2d.CArray2D;
import com.tambapps.math.complex.Complex;

/**
 * Util functions for Fourier Transform
 */
public class FFTUtils {

  private FFTUtils() {
  }

  /**
   * Changes the center of the Fourier Transform
   * @param array the FT
   */
  public static void changeCenter(CArray2D array) {
    for (int i = 0; i < array.getM() / 2; i++) {
      for (int j = 0; j < array.getN() / 2; j++) {
        swap(array, i, j, i + array.getM() / 2, j + array.getN() / 2);
        swap(array, i, j + array.getN() / 2, i + array.getM() / 2, j);
      }
    }
  }

  private static void swap(CArray2D array, int i1, int j1, int i2, int j2) {
    Complex temp = array.get(i1, j1);
    array.set(i1, j1, array.get(i2, j2));
    array.set(i2, j2, temp);
  }

  /**
   * Makes a padded copy of the given array
   * @param array an array of values
   * @param padding the padding wanted
   * @return the padded copy of the given array
   */
  public static CArray2D paddedCopy(CArray2D array, Padding padding) {
    if (padding.equals(Padding.ZERO)) {
      return array.copy();
    }
    return paddedCopy(array, padding.getLeft(), padding.getRight(), padding.getTop(),
        padding.getEnd());
  }

  /**
   * Makes a padded copy of the given array
   *
   * @param array        an array of values
   * @param paddingLeft  the left padding
   * @param paddingRight the right padding
   * @param paddingTop   the top padding
   * @param paddingEnd   the bottom padding
   * @return the padded copy of the given array
   */
  public static CArray2D paddedCopy(CArray2D array, int paddingLeft, int paddingRight,
      int paddingTop, int paddingEnd) {
    CArray2D copy = new CArray2D(array.getM() + paddingTop + paddingEnd,
        array.getN() + paddingLeft + paddingRight);
    for (int i = 0; i < copy.getM(); i++) {
      for (int j = 0; j < copy.getN(); j++) {
        if (i < paddingEnd || i - paddingEnd >= array.getM() ||
            j < paddingLeft || j - paddingLeft >= array.getN()) {
          copy.set(i, j, Complex.ZERO);
        } else {
          copy.set(i, j, array.get(i - paddingEnd, j - paddingLeft));
        }
      }
    }
    return copy;
  }

  /**
   * Makes an unpadded copy of the given array
   * @param array an array of values
   * @param padding the padding wanted
   * @return the unpadded copy of the given array
   */
  public static CArray2D unpaddedCopy(CArray2D array, Padding padding) {
    if (padding.equals(Padding.ZERO)) {
      return array.copy();
    }
    return unpaddedCopy(array, padding.getLeft(), padding.getRight(), padding.getTop(),
        padding.getEnd());
  }

  /**
   * Makes an unpadded copy of the given array
   *
   * @param array        an array of values
   * @param paddingLeft  the left padding
   * @param paddingRight the right padding
   * @param paddingTop   the top padding
   * @param paddingEnd   the bottom padding
   * @return the unpadded copy of the given array
   */
  public static CArray2D unpaddedCopy(CArray2D array, int paddingLeft, int paddingRight,
      int paddingTop, int paddingEnd) {
    CArray2D copy = new CArray2D(array.getM() - paddingTop - paddingEnd,
        array.getN() - paddingLeft - paddingRight);
    for (int i = 0; i < copy.getM(); i++) {
      for (int j = 0; j < copy.getN(); j++) {
        copy.set(i, j, array.get(i + paddingEnd, j + paddingLeft));
      }
    }
    return copy;
  }

}
