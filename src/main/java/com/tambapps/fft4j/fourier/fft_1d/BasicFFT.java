package com.tambapps.fft4j.fourier.fft_1d;

import com.tambapps.fft4j.complex.Complex;
import com.tambapps.fft4j.complex.vector.ArrayCVector;
import com.tambapps.fft4j.complex.vector.CVector;

/**
 * The basic algorithm for the FFT
 *
 */
class BasicFFT implements FastFourierTransform {

  @Override
  public void compute(CVector vector) {
    CVector result = computeCopy(vector);
    result.copy(vector);
  }

  @Override
  public CVector computeCopy(CVector vector) {
    CVector result = new ArrayCVector(vector.getSize());
    double N = vector.getSize();
    for (int k = 0; k < vector.getSize(); k++) {
      Complex sum = Complex.ZERO;
      for (int n = 0; n < vector.getSize(); n++) {
        sum = sum.plus(vector.getAt(n).multiply(
            Complex.expI(-2d * Math.PI * ((double) k) * ((double) n) / N)));
      }
      result.setAt(k, sum);
    }
    return result;
  }

  @Override
  public String getName() {
    return "Basic";
  }

  @Override
  public String getDescription() {
    return "Compute the sums like in the basic FFT formula";
  }
}