package com.tambapps.fft4j.fourier.filtering;

import com.tambapps.fft4j.complex.array2d.CArray2D;

/**
 * A filter of a Fourier Transform
 */
public interface Filter {

  /**
   * Applies the filter on the given array
   *
   * @param array the array to filter
   */
  void apply(CArray2D array);

  /**
   * same as apply (util for Groovy operator)
   *
   * @param array the array to filter
   */
  void call(CArray2D array);

  /**
   * Applies the filter on a copied array and returns it
   *
   * @param array the array to filter
   * @return the filtered array
   */
  CArray2D applied(CArray2D array);

}
