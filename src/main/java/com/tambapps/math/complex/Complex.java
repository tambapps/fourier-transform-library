package com.tambapps.math.complex;

import static com.tambapps.math.util.DoubleTester.equal;

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

  public boolean isPureImaginary() {
    return equal(0d, real);
  }

  public boolean isPureReal() {
    return equal(0d, imaginary);
  }

  public Complex multiply(double scl) {
    return new Complex(scl * real, scl * imaginary);
  }

  public Complex negative() {
    return new Complex(-real, -imaginary);
  }

  public Complex positive() {
    return this;
  }

  public double abs() {
    return Math.hypot(real, imaginary);
  }

  public double getAt(int i) {
    if (i > 1 || i < 0) {
      throw new IndexOutOfBoundsException("There is only two fields in a complex number");
    }
    return i == 0 ? real : imaginary;
  }

  public Complex plus(Complex complex) {
    return new Complex(real + complex.real, imaginary + complex.imaginary);
  }

  public Complex plus(Double d) {
    return new Complex(real + d, imaginary);
  }

  public Complex minus(Complex complex) {
    return new Complex(real - complex.real, imaginary - complex.imaginary);
  }

  public Complex div(Complex complex) {
    throw new UnsupportedOperationException("not implemented yet");
  }

  public Complex div(double number) {
    if (equal(number, 0d)) {
      throw new IllegalArgumentException("Cannot divide by 0");
    }
    return new Complex(real / number, imaginary / number);
  }

  public Complex conj() {
    return new Complex(real, -imaginary);
  }

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

  public static Complex of(double real, double imaginary) {
    return new Complex(real, imaginary);
  }

  public static Complex of(double real) {
    return new Complex(real, 0d);
  }

  //exp(i*x)
  public static Complex expI(double x) {
    return of(Math.cos(x), Math.sin(x));
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
