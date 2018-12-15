package com.tambapps.fft4j.util;

import com.tambapps.fft4j.complex.Complex;

/**
 * Vector holding complex numbers
 */
public interface CVector {

  /**
   * Returns the complex at the index i
   *
   * @param i the index
   * @return the i-th complex
   */
  Complex getAt(int i);

  /**
   * Sets the complex at the index o
   *
   * @param i     the index
   * @param value the value to set
   */
  void setAt(int i, Complex value);

  /**
   * Returns the size of the vector
   *
   * @return the size of the vector
   */
  int getSize();

}
