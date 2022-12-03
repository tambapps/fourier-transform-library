package com.tambapps.fft4j;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Random;

public class FastFourier2dTransformerTest {

  private final static double LIMIT = 1024d;

  private final FastFourier2dTransformer transformer2D = new FastFourier2dTransformer();

  @Test
  public void testSuccess() {
    final int M = 300;
    final int N = 256;
    Random random = new Random();
    Signal2d array2D = new Signal2d(M, N);
    for (int i = 0; i < M * N; i++) {
      array2D.setReAt(i, random.nextDouble() % LIMIT);
      array2D.setImAt(i, random.nextDouble() % LIMIT);
    }

    Signal2d originArray = new Signal2d(M, N);
    array2D.copyInto(originArray);
    transformer2D.transform(array2D);
    transformer2D.inverse(array2D);


    for (int i = 0; i < M * N; i++) {
      assertEquals("Should be equal",originArray.getReAt(i), array2D.getReAt(i), 0.01);
      assertEquals("Should be equal",originArray.getReAt(i), array2D.getReAt(i), 0.01);
    }
  }
}
