package com.tambapps.fft4j;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class FastFouriersTest {

  private static final double DELTA = 0.01d;
  private static final double[] INPUT_RE = new double[] {
      1d, 1d, 1d, 1d,
      0d, 0d, 0d, 0d
  };

  private static final double[] INPUT_IM = new double[] {
      0d, 0d, 0d, 0d,
      0d, 0d, 0d, 0d
  };
  private final double[] EXPECTED_RE = new double[] {
      4d,
      1d,
      0d,
      1d,
      0d,
      1d,
      0d,
      1d
  };
  private final double[] EXPECTED_IM = new double[] {
      0d,
      -2.414214,
      0d,
      -0.414214,
      0d,
      0.414214,
      0d,
      2.414214
  };
  @Test
  public void basicTest() {
    double[] inputRe = Arrays.copyOf(INPUT_RE, INPUT_RE.length);
    double[] inputIm = Arrays.copyOf(INPUT_IM, INPUT_RE.length);

    double[] outputRe = new double[inputRe.length];
    double[] outputIm = new double[inputRe.length];
    FastFouriers.BASIC.transform(inputRe, inputIm, outputRe, outputIm);

    assertArrayEquals(EXPECTED_RE, outputRe, DELTA);
    assertArrayEquals(EXPECTED_IM, outputIm, DELTA);
  }

  @Test
  public void iterativeCooleyTukeyTest() {
    double[] inputRe = Arrays.copyOf(INPUT_RE, INPUT_RE.length);
    double[] inputIm = Arrays.copyOf(INPUT_IM, INPUT_RE.length);

    double[] outputRe = new double[inputRe.length];
    double[] outputIm = new double[inputRe.length];
    FastFouriers.ITERATIVE_COOLEY_TUKEY.transform(inputRe, inputIm, outputRe, outputIm);

    assertArrayEquals(EXPECTED_RE, outputRe, DELTA);
    assertArrayEquals(EXPECTED_IM, outputIm, DELTA);
  }

  @Test
  public void recursiveCooleyTukeyTest() {
    double[] inputRe = Arrays.copyOf(INPUT_RE, INPUT_RE.length);
    double[] inputIm = Arrays.copyOf(INPUT_IM, INPUT_RE.length);

    double[] outputRe = new double[inputRe.length];
    double[] outputIm = new double[inputRe.length];
    FastFouriers.RECURSIVE_COOLEY_TUKEY.transform(inputRe, inputIm, outputRe, outputIm);

    assertArrayEquals(EXPECTED_RE, outputRe, DELTA);
    assertArrayEquals(EXPECTED_IM, outputIm, DELTA);
  }


  @Test
  public void basicInverseTest() {
    double[] re = Arrays.copyOf(EXPECTED_RE, INPUT_RE.length);
    double[] im = Arrays.copyOf(EXPECTED_IM, INPUT_RE.length);

    FastFouriers.BASIC.inverse(re, im);


    assertArrayEquals(INPUT_RE, re, DELTA);
    assertArrayEquals(INPUT_IM, im, DELTA);
  }

  @Test
  public void iterativeCooleyTukeyInverseTest() {
    double[] re = Arrays.copyOf(EXPECTED_RE, INPUT_RE.length);
    double[] im = Arrays.copyOf(EXPECTED_IM, INPUT_RE.length);

    FastFouriers.ITERATIVE_COOLEY_TUKEY.inverse(re, im);


    assertArrayEquals(INPUT_RE, re, DELTA);
    assertArrayEquals(INPUT_IM, im, DELTA);
  }

  @Test
  public void recursiveCooleyTukeyInverseTest() {
    double[] re = Arrays.copyOf(EXPECTED_RE, INPUT_RE.length);
    double[] im = Arrays.copyOf(EXPECTED_IM, INPUT_RE.length);

    FastFouriers.RECURSIVE_COOLEY_TUKEY.inverse(re, im);


    assertArrayEquals(INPUT_RE, re, DELTA);
    assertArrayEquals(INPUT_IM, im, DELTA);

  }

}
