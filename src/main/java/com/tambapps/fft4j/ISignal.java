package com.tambapps.fft4j;

/**
 * Interface of a Signal
 */
public interface ISignal {

  /**
   * Returns the real part of the ith number of the signal
   *
   * @param i the index
   * @return the real part of the ith number of the signal
   */
  double getReAt(int i);

  /**
   * Returns the imaginary part of the ith number of the signal
   *
   * @param i the index
   * @return the imaginary part of the ith number of the signal
   */
  double getImAt(int i);

  /**
   * Sets the real part of the ith number of the signal
   *
   * @param i     the index
   * @param value the value to set
   */
  void setReAt(int i, double value);

  /**
   * Sets the imaginary part of the ith number of the signal
   *
   * @param i     the index
   * @param value the value to set
   */
  void setImAt(int i, double value);


  /**
   * Sets the real and imaginary part of the ith number of the signal
   *
   * @param i  the index
   * @param re the real part
   * @param im the imaginary part
   */
  default void setAt(int i, double re, double im) {
    setReAt(i, re);
    setImAt(i, im);
  }

  /**
   * Sets the signal from the values of the specified arrays. Values are copied from the array to the signal
   *
   * @param re the real parts
   * @param im the imaginary parts
   */
  default void setFrom(double[] re, double[] im) {
    if (getLength() != re.length || getLength() != im.length) {
      throw new IllegalArgumentException("Cannot set signal from arrays with different lengths");
    }
    for (int i = 0; i < getLength(); i++) {
      setReAt(i, re[i]);
      setImAt(i, im[i]);
    }
  }

  /**
   * Copy this signal into the specified arrays
   *
   * @param re the real parts
   * @param im the imaginary parts
   */
  default void copyInto(double[] re, double[] im) {
    if (getLength() != re.length || getLength() != im.length) {
      throw new IllegalArgumentException("Cannot copy signal in arrays with different lengths");
    }
    for (int i = 0; i < getLength(); i++) {
      re[i] = getReAt(i);
      im[i] = getImAt(i);
    }
  }

  /**
   * Copy this signal into the provided one
   *
   * @param signal the signal in which to copy
   */
  default void copyInto(ISignal signal) {
    if (getLength() != signal.getLength()) {
      throw new IllegalArgumentException("Cannot copy signal in another with different length");
    }
    for (int i = 0; i < getLength(); i++) {
      signal.setReAt(i, getReAt(i));
      signal.setImAt(i, getImAt(i));
    }
  }

  /**
   * Returns the length of the signal
   *
   * @return the length of the signal
   */
  int getLength();
}
