package com.tambapps.fft4j.fourier.fft_1d;

import static org.junit.Assert.assertEquals;

import com.tambapps.fft4j.complex.Complex;
import com.tambapps.fft4j.util.CVector;

import com.tambapps.fft4j.util.ImmutableCVector;
import org.junit.Test;

import java.util.Arrays;

public class FourierAlgorithmsTest {

  private final static Complex ONE = Complex.of(1);
  private final CVector input = ImmutableCVector.of(ONE,
      ONE,
      ONE,
      ONE,
      Complex.ZERO,
      Complex.ZERO,
      Complex.ZERO,
      Complex.ZERO);

  private final CVector expected = ImmutableCVector.of(Complex.of(4),
      Complex.of(1d, -2.414214),
      Complex.ZERO,
      Complex.of(1, -0.414214),
      Complex.ZERO,
      Complex.of(1, 0.414214),
      Complex.ZERO,
      Complex.of(1, 2.414214));

  private void algorithmTest(FFTAlgorithm algorithm) {
    CVector result = input.copy();
    algorithm.compute(result);
    assertEquals("Should be equal", expected, result);
    assertEquals("Should be equal", expected, algorithm.computeCopy(input));
  }

  @Test
  public void basicTest() {
    algorithmTest(FourierAlgorithms.BASIC);
  }

  @Test
  public void recursiveTest() {
    algorithmTest(FourierAlgorithms.CT_RECURSIVE);
  }

  @Test
  public void iterativeTest() {
    algorithmTest(FourierAlgorithms.CT_ITERATIVE);
  }

  @Test
  public void inverseTest() {
    for (FFTAlgorithm algorithm : Arrays.asList(FourierAlgorithms.CT_ITERATIVE,
      FourierAlgorithms.BASIC, FourierAlgorithms.CT_RECURSIVE)) {
      CVector result = expected.copy();
      FourierAlgorithms.inverse(algorithm).compute(result);
      assertEquals("Should be equal", input, result);
    }
  }

}