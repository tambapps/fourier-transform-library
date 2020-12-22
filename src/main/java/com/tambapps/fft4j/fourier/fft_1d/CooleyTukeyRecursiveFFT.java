package com.tambapps.fft4j.fourier.fft_1d;

import com.tambapps.fft4j.complex.Complex;
import com.tambapps.fft4j.complex.vector.ArrayCVector;
import com.tambapps.fft4j.complex.vector.CVector;

/**
 * Compute the FFT in the given vector
 * with the recursive Cooley-Tukey algorithm
 *
 * @link from https://rosettacode.org/wiki/Fast_Fourier_transform
 */
public class CooleyTukeyRecursiveFFT implements FastFourierTransform {

  @Override
  public void compute(CVector vector) {
    computeCopy(vector).copy(vector);
  }

  @Override
  public CVector computeCopy(CVector vector) {
    int N = vector.getSize();
    if (N <= 1) {
      return vector;
    }
    CVector evens = computeCopy(evensCopy(vector));
    CVector odds = computeCopy(oddsCopy(vector));

    Complex[] T = new Complex[N / 2];
    for (int i = 0; i < N / 2; i++) {
      T[i] = odds.getAt(i).multiply(Complex.expI(-2d * Math.PI * ((double) i) / ((double) N)));
    }

    CVector result = new ArrayCVector(N);
    for (int i = 0; i < N / 2; i++) {
      result.setAt(i, evens.getAt(i).plus(T[i]));
      result.setAt(i + N / 2, evens.getAt(i).minus(T[i]));
    }
    return result;
  }

  private CVector evensCopy(CVector vector) {
    int size = (vector.getSize() + 1) / 2;

    CVector copy = new ArrayCVector(size);
    int count = 0;
    for (int i = 0; i < vector.getSize(); i += 2) {
      copy.setAt(count, vector.getAt(i));
      count++;
    }
    return copy;
  }

  private CVector oddsCopy(CVector vector) {
    int size = vector.getSize() / 2;

    CVector copy = new ArrayCVector(size);
    int count = 0;
    for (int i = 1; i < vector.getSize(); i += 2) {
      copy.setAt(count, vector.getAt(i));
      count++;
    }
    return copy;
  }

  @Override
  public String getName() {
    return "Cooley-Tukey recursive";
  }

  @Override
  public String getDescription() {
    return "Cooley-Tukey algorithm implemented recursively (input array must have power of 2 sizes)";
  }
}