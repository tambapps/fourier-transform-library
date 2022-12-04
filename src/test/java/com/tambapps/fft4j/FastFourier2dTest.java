package com.tambapps.fft4j;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import java.util.Random;

public class FastFourier2dTest {

  private static final double DELTA = 0.01d;

  private final static double LIMIT = 1024d;

  private final FastFourier2d transformer2D = new FastFourier2d();

  @Test
  public void testSuccess() {
    final int M = 300;
    final int N = 256;
    Random random = new Random(1);
    Signal2d array2D = new Signal2d(M, N);
    for (int i = 0; i < M * N; i++) {
      array2D.setReAt(i, random.nextDouble() % LIMIT);
    //  array2D.setImAt(i, random.nextDouble() % LIMIT);
    }

    Signal2d originArray = new Signal2d(M, N);
    array2D.copyInto(originArray);

    transformer2D.transform(array2D);
    transformer2D.inverse(array2D);

    assertArrayEquals("Should be equal",originArray.getRe(), array2D.getRe(), DELTA);
    assertArrayEquals("Should be equal",originArray.getIm(), array2D.getIm(), DELTA);

  }

  @Test
  public void test() {
    double[] inputRe = new double[] {
        1d, 1d, 1d, 1d,
        0d, 0d, 0d, 0d
    };
    double[] inputIm = new double[] {
        0d, 0d, 0d, 0d,
        0d, 0d, 0d, 0d
    };

    Signal2d signal2d = new Signal2d(inputRe.length, 1, inputRe, inputIm);
    transformer2D.transform(signal2d);

    final double[] expectedRe = new double[] {
        4d,
        1d,
        0d,
        1d,
        0d,
        1d,
        0d,
        1d
    };
    final double[] expectedIm = new double[] {
        0d,
        -2.414214,
        0d,
        -0.414214,
        0d,
        0.414214,
        0d,
        2.414214
    };
    assertArrayEquals(expectedRe, signal2d.getRe(), DELTA);
    assertArrayEquals(expectedIm, signal2d.getIm(), DELTA);

    transformer2D.inverse(signal2d);

    assertArrayEquals(new double[] {
        1d, 1d, 1d, 1d,
        0d, 0d, 0d, 0d
    }, signal2d.getRe(), DELTA);
    assertArrayEquals(new double[] {
        0d, 0d, 0d, 0d,
        0d, 0d, 0d, 0d
    }, signal2d.getIm(), DELTA);
  }
}
