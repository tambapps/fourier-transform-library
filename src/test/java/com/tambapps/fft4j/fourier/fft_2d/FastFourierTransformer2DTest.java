package com.tambapps.fft4j.fourier.fft_2d;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.tambapps.fft4j.carray2d.CArray2D;
import com.tambapps.fft4j.complex.Complex;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Executors;

public class FastFourierTransformer2DTest {

  private final static double LIMIT = 1024d;

  private final FastFourierTransformer2D transformer2D = new FastFourierTransformer2D(
      Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1));

  @Test
  public void testSuccess() {
    final int N = 256;
    Random random = new Random();
    CArray2D array2D = new CArray2D(N, N);
    for (int i = 0; i < N * N; i++) {
      array2D.set(i, Complex.of(random.nextDouble() % LIMIT));
    }

    CArray2D originArray = array2D.copy();
    assertTrue("Should be true", transformer2D.transform(array2D));
    assertTrue("Should be true", transformer2D.inverse(array2D));


    for (int i = 0; i < N * N; i++) {
      assertEquals("Should be equal",originArray.get(i), array2D.get(i));
    }
  }
}
