package com.tambapps.fft4j.fourier.filtering;

import com.tambapps.fft4j.complex.array2d.CArray2D;
import com.tambapps.fft4j.complex.Complex;

/**
 * An abstract filter. Useful to extends this class when creating a new filter implementation
 */
public abstract class AbstractFilter implements Filter {

  @Override
  public final void apply(CArray2D array) {
    apply(array, array);
  }

  private void apply(CArray2D input, CArray2D output) {
    final int M = input.getM();
    final int N = input.getN();
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        output.set(i, j, apply(input.get(i, j), i, j, M, N));
      }
    }
  }

  @Override
  public final CArray2D applied(CArray2D array) {
    CArray2D result = new CArray2D(array.getM(), array.getN());
    apply(array, result);
    return result;
  }

  abstract Complex apply(Complex c, int i, int j, int M, int N);

}
