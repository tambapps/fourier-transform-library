package com.tambapps.fft4j.fourier.fft_2d;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.tambapps.fft4j.complex.array2d.CArray2D;
import com.tambapps.fft4j.complex.Complex;
import com.tambapps.fft4j.complex.vector.CVector;
import com.tambapps.fft4j.complex.vector.ImmutableCVector;
import org.junit.Test;

import java.util.Random;

public class FastFourierTransformer2DTest {

  private final static double LIMIT = 1024d;

  private final FastFourierTransformer2D transformer2D = new FastFourierTransformer2D();

  @Test
  public void testSuccess() {
    final int M = 300;
    final int N = 256;
    Random random = new Random();
    CArray2D array2D = new CArray2D(M, N);
    for (int i = 0; i < M * N; i++) {
      array2D.set(i, Complex.of(random.nextDouble() % LIMIT));
    }

    CArray2D originArray = array2D.copy();
    assertTrue("Should be true", transformer2D.transform(array2D));
    assertTrue("Should be true", transformer2D.inverse(array2D));


    for (int i = 0; i < M * N; i++) {
      assertEquals("Should be equal",originArray.get(i), array2D.get(i));
    }
  }

  @Test
  public void test() {
    Complex ONE = Complex.of(1);
    final Complex[] input = new Complex[] {
        ONE,
        ONE,
        ONE,
        ONE,
        Complex.ZERO,
        Complex.ZERO,
        Complex.ZERO,
        Complex.ZERO
    };

    CArray2D array2D = new CArray2D(input.length, 1, input);

    assertTrue("Should be true", transformer2D.transform(array2D));


    final CVector expected = ImmutableCVector.of(Complex.of(4),
        Complex.of(1d, -2.414214),
        Complex.ZERO,
        Complex.of(1, -0.414214),
        Complex.ZERO,
        Complex.of(1, 0.414214),
        Complex.ZERO,
        Complex.of(1, 2.414214));

    assertEquals("Should be equal", expected, array2D.getColumn(0));
  }

}
