package com.tambapps.math.complex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ComplexTest {

  @Test
  public void addTest() {
    Complex complex1 = Complex.of(8);
    Complex complex2 = Complex.of(4, 1);

    assertEquals("Should be equal", Complex.of(12, 1), complex1.plus(complex2));
  }

  @Test
  public void pureTest() {
    Complex pr = Complex.of(3423);
    Complex pi = Complex.of(0, 234);

    assertTrue("Should be only pure real", pr.isPureReal() && !pr.isPureImaginary());
    assertTrue("Should be only pure imaginary", pi.isPureImaginary() && !pi.isPureReal());
  }

  @Test
  public void mulTest() {
    Complex c1 = Complex.ONE;
    Complex c2 = Complex.of(0, 2);

    assertEquals("Should be equal", Complex.ZERO, c1.multiply(Complex.ZERO));
    assertEquals("Should be equal", Complex.ZERO, Complex.ZERO.multiply(c1));

    assertEquals("Should be equal", Complex.I.multiply(2), c1.multiply(c2));
    assertEquals("Should be equal", Complex.I.multiply(2), c2.multiply(c1));
  }

}
