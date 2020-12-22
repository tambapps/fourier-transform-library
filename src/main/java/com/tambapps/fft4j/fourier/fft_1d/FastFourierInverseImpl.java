package com.tambapps.fft4j.fourier.fft_1d;

import com.tambapps.fft4j.complex.vector.CVector;

/**
 * Inverse a Fourier Transformed vector using a given FFT algorithm
 *
 */
public class FastFourierInverseImpl implements FastFourierInverse {
  private final FastFourierTransform algorithm;

  public FastFourierInverseImpl(FastFourierTransform algorithm) {
    this.algorithm = algorithm;
  }

  @Override
  public void compute(CVector vector) {
    for (int i = 0; i < vector.getSize(); i++) {
      vector.setAt(i, vector.getAt(i).conj());
    }

    algorithm.compute(vector);

    double iN = 1d / ((double) vector.getSize());
    for (int i = 0; i < vector.getSize(); i++) {
      vector.setAt(i, vector.getAt(i).conj().multiply(iN));
    }
  }

  @Override
  public void call(CVector vector) {
    compute(vector);
  }
}