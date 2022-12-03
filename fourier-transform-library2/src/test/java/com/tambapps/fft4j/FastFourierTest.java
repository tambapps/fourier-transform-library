package com.tambapps.fft4j;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.fail;

public class FastFourierTest {
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
    FastFourier.BASIC.transform(inputRe, inputIm, outputRe, outputIm);

    assertDoubleArrayEquals(EXPECTED_RE, outputRe);
    assertDoubleArrayEquals(EXPECTED_IM, outputIm);
  }

  @Test
  public void iterativeCooleyTukeyTest() {
    double[] inputRe = Arrays.copyOf(INPUT_RE, INPUT_RE.length);
    double[] inputIm = Arrays.copyOf(INPUT_IM, INPUT_RE.length);

    double[] outputRe = new double[inputRe.length];
    double[] outputIm = new double[inputRe.length];
    FastFourier.ITERATIVE_COOLEY_TUKEY.transform(inputRe, inputIm, outputRe, outputIm);

    assertDoubleArrayEquals(EXPECTED_RE, outputRe);
    assertDoubleArrayEquals(EXPECTED_IM, outputIm);
  }

  @Test
  public void recursiveCooleyTukeyTest() {
    double[] inputRe = Arrays.copyOf(INPUT_RE, INPUT_RE.length);
    double[] inputIm = Arrays.copyOf(INPUT_IM, INPUT_RE.length);

    double[] outputRe = new double[inputRe.length];
    double[] outputIm = new double[inputRe.length];
    FastFourier.RECURSIVE_COOLEY_TUKEY.transform(inputRe, inputIm, outputRe, outputIm);

    assertDoubleArrayEquals(EXPECTED_RE, outputRe);
    assertDoubleArrayEquals(EXPECTED_IM, outputIm);
  }


  @Test
  public void basicInverseTest() {
    double[] re = Arrays.copyOf(EXPECTED_RE, INPUT_RE.length);
    double[] im = Arrays.copyOf(EXPECTED_IM, INPUT_RE.length);

    FastFourier.BASIC.inverse(re, im);


    assertDoubleArrayEquals(INPUT_RE, re);
    assertDoubleArrayEquals(INPUT_IM, im);
  }

  @Test
  public void iterativeCooleyTukeyInverseTest() {
    double[] re = Arrays.copyOf(EXPECTED_RE, INPUT_RE.length);
    double[] im = Arrays.copyOf(EXPECTED_IM, INPUT_RE.length);

    FastFourier.ITERATIVE_COOLEY_TUKEY.inverse(re, im);


    assertDoubleArrayEquals(INPUT_RE, re);
    assertDoubleArrayEquals(INPUT_IM, im);
  }

  @Test
  public void recursiveCooleyTukeyInverseTest() {
    double[] re = Arrays.copyOf(EXPECTED_RE, INPUT_RE.length);
    double[] im = Arrays.copyOf(EXPECTED_IM, INPUT_RE.length);

    FastFourier.RECURSIVE_COOLEY_TUKEY.inverse(re, im);


    assertDoubleArrayEquals(INPUT_RE, re);
    assertDoubleArrayEquals(INPUT_IM, im);
  }

  private void assertDoubleArrayEquals(double[] expected, double[] actual) {
    for (int i = 0; i < expected.length; i++) {
      if (Math.abs(expected[i] - actual[i]) > 0.01d) {
        fail(String.format("Array are not similar. Expected %s but got %s",
            Arrays.toString(expected), Arrays.toString(actual)));
      }
    }
  }
}
