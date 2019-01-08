package com.tambapps.fft4j.complex;

import static com.tambapps.fft4j.util.DoubleTester.equal;

import java.util.Objects;

/**
 * Representation of a complex number
 */
public class Complex {

  public static final Complex I = new Complex(0d, 1d);
  public static final Complex ZERO = new Complex(0d, 0d);
  public static final Complex ONE = new Complex(1d, 0d);

  private final double real;
  private final double imaginary;

  private Complex(double real, double imaginary) {
    this.real = real;
    this.imaginary = imaginary;
  }

  /**
   * Creates a complex number with the given values
   *
   * @param real      the real part
   * @param imaginary the imaginary part
   * @return the complex number
   */
  public static Complex of(double real, double imaginary) {
    return new Complex(real, imaginary);
  }

  /**
   * Creates a pure real complex number
   *
   * @param real the real part
   * @return a pure real complex number
   */
  public static Complex of(double real) {
    return new Complex(real, 0d);
  }

  /**
   * Creates an exponential complex number where <br>
   * real part = cos(x)
   * imaginary part = sin(x)
   *
   * @param x the angle in radians
   * @return the exponential complex number
   */
  public static Complex expI(double x) {
    return of(Math.cos(x), Math.sin(x));
  }

  /**
   * Returns whether this number is pure imaginary (null real part)
   *
   * @return whether it is a pure imaginary number
   */
  public boolean isPureImaginary() {
    return equal(0d, real);
  }

  /**
   * Returns whether this number is pure real (null imaginary part)
   *
   * @return whether it is a real number
   */
  public boolean isPureReal() {
    return equal(0d, imaginary);
  }

  /**
   * Computes scales this complex number with the given scalar
   *
   * @param scl the scalar
   * @return the scaled number
   */
  public Complex multiply(double scl) {
    return new Complex(scl * real, scl * imaginary);
  }

  /**
   * Returns the negate of this number
   *
   * @return the negate
   */
  public Complex negative() {
    return new Complex(-real, -imaginary);
  }

  /**
   * Returns this number
   *
   * @return this
   */
  public Complex positive() {
    return this;
  }

  /**
   * Returns the absolute value (or modulus) of this complex
   *
   * @return the modulus
   */
  public double abs() {
    return Math.hypot(real, imaginary);
  }

  /**
   * Util function to access complex fields as an array <br>
   * index 0 is the real part <br>
   * index 1 is the imaginary part
   *
   * @param i the index
   * @return the associated field number
   */
  public double getAt(int i) {
    if (i > 1 || i < 0) {
      throw new IndexOutOfBoundsException("There is only two fields in a complex number");
    }
    return i == 0 ? real : imaginary;
  }

  /**
   * Returns the addition between this number and the argument
   *
   * @param complex the argument
   * @return the sum of this number and the argument
   */
  public Complex plus(Complex complex) {
    return new Complex(real + complex.real, imaginary + complex.imaginary);
  }

  /**
   * Returns the addition between this number and the argument
   *
   * @param d the argument
   * @return the sum of this number and the argument
   */
  public Complex plus(Double d) {
    return new Complex(real + d, imaginary);
  }

  /**
   * Returns this number subtracted by the argument
   *
   * @param complex the argument
   * @return the subtraction between this number and the argument
   */
  public Complex minus(Complex complex) {
    return new Complex(real - complex.real, imaginary - complex.imaginary);
  }

  /**
   * Returns this number subtracted by the argument
   *
   * @param d the argument
   * @return the subtraction between this number and the argument
   */
  public Complex minus(Double d) {
    return new Complex(real - d, imaginary);
  }

  /**
   * Returns this number divided by the argument
   *
   * @param complex the argument
   * @return the division of this number by the argument
   */
  public Complex div(Complex complex) {
    double denom = complex.real * complex.real + complex.imaginary * complex.imaginary;
    return new Complex((real * complex.real + imaginary * complex.imaginary) / denom,
      (imaginary * complex.real - real * complex.imaginary) / denom);
  }

  /**
   * Returns this number divided by the argument
   *
   * @param d the argument
   * @return the division of this number by the argument
   */
  public Complex div(Double d) {
    if (equal(d, 0d)) {
      throw new IllegalArgumentException("Cannot divide by 0");
    }
    return new Complex(real / d, imaginary / d);
  }

  /**
   * Returns the conjugate of this number
   *
   * @return the conjugate
   */
  public Complex conj() {
    return new Complex(real, -imaginary);
  }

  /**
   * Returns the multiplication between this number and the argument
   *
   * @param complex the argument
   * @return the multiplication of this number and the argument
   */
  public Complex multiply(Complex complex) {
    double real = this.real * complex.real - this.imaginary * complex.imaginary;
    double imaginary = this.real * complex.imaginary + this.imaginary * complex.real;
    return new Complex(real, imaginary);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (!equal(0d, real)) {
      builder.append(real);
      if (!equal(0d, imaginary)) {
        builder.append(" + i * ").append(imaginary);
      }
    } else {
      if (!equal(0d, imaginary)) {
        builder.append("i * ").append(imaginary);
      } else {
        builder.append("0");
      }
    }
    return builder.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Complex complex = (Complex) o;
    return equal(complex.real, real) &&
      equal(complex.imaginary, imaginary);
  }

  @Override
  public int hashCode() {
    return Objects.hash(real, imaginary);
  }

}
