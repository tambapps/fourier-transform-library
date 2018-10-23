package com.tambapps.math.fourier.filtering;

import com.tambapps.math.carray2d.CArray2D;
import com.tambapps.math.complex.Complex;

public abstract class AbstractFilter implements Filter {

  @Override
  public final void apply(CArray2D array) {
    final int M = array.getM();
    final int N = array.getN();
    before(M, N);
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        array.set(i, j, apply(array.get(i, j), i, j, M, N));
      }
    }
  }

  @Override
  public void call(CArray2D array) {
    apply(array);
  }

  void before(int M, int N) {
  }

  @Override
  public final CArray2D applied(CArray2D array) {
    final int M = array.getM();
    final int N = array.getN();
    CArray2D result = new CArray2D(M, N);
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < N; j++) {
        result.set(i, j, apply(array.get(i, j), i, j, M, N));
      }
    }
    return result;
  }

  abstract Complex apply(Complex c, int i, int j, int M, int N);

}
