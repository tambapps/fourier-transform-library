package com.tambapps.fft4j.fourier.fft_1d;

import static org.junit.Assert.assertEquals;

import com.tambapps.fft4j.complex.Complex;
import com.tambapps.fft4j.util.ArrayCVector;
import com.tambapps.fft4j.util.CVector;

import com.tambapps.fft4j.util.CVectorUtils;
import org.junit.Test;

public class FourierAlgorithmsTest {

  private final static Complex ONE = Complex.of(1);
  private final CVector input = CVectorUtils.of(ONE,
      ONE,
      ONE,
      ONE,
      Complex.ZERO,
      Complex.ZERO,
      Complex.ZERO,
      Complex.ZERO);

  private final CVector expected = CVectorUtils.of(Complex.of(4),
      Complex.of(1d, -2.414214),
      Complex.ZERO,
      Complex.of(1, -0.414214),
      Complex.ZERO,
      Complex.of(1, 0.414214),
      Complex.ZERO,
      Complex.of(1, 2.414214));

  @Test
  public void basicTest() {
    CVector result = new ArrayCVector(input.getSize());
    CVectorUtils.copy(input, result);
    FourierAlgorithms.BASIC.compute(result);
    assertEquals("Should be equal", expected, result);
  }

  @Test
  public void recursiveTest() {
    CVector result = new ArrayCVector(input.getSize());
    CVectorUtils.copy(input, result);
    FourierAlgorithms.CT_RECURSIVE.compute(result);
    assertEquals("Should be equal", expected, result);
  }

  @Test
  public void iterativeTest() {
    CVector result = new ArrayCVector(input.getSize());
    CVectorUtils.copy(input, result);
    FourierAlgorithms.CT_ITERATIVE.compute(result);
    assertEquals("Should be equal", expected, result);
  }

  @Test
  public void inverseTest() {
    for (FFTAlgorithm algorithm : new FFTAlgorithm[] {FourierAlgorithms.CT_ITERATIVE,
        FourierAlgorithms.BASIC, FourierAlgorithms.CT_RECURSIVE}) {
      CVector result = new ArrayCVector(expected.getSize());
      CVectorUtils.copy(expected, result);
      FourierAlgorithms.INVERSE.compute(result, algorithm);
      assertEquals("Should be equal", input, result);
    }
  }

}