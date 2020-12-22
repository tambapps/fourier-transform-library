package com.tambapps.fft4j.fourier.fft_1d;

import com.tambapps.fft4j.complex.Complex;
import com.tambapps.fft4j.complex.vector.CVector;
import com.tambapps.fft4j.fourier.util.Utils;

/**
 * Compute the 1D FFT in the given vector
 * with the iterative Cooley-Tukey algorithm
 * The computation is made in the given vector
 * (not very precise for large 2D arrays)
 *
 * @link from https://rosettacode.org/wiki/Fast_Fourier_transform#Java
 */
public class CooleyTukeyIterativeFFT implements FastFourierTransform {

  @Override
  public void compute(CVector vector) {
    int n = vector.getSize();

    int bits = Utils.get2Exponent(n);
    bitReverseVector(vector, bits);

    for (int m = 2; m <= n; m <<= 1) {
      for (int i = 0; i < n; i += m) {
        for (int k = 0; k < m / 2; k++) {
          int evenIndex = i + k;
          int oddIndex = i + k + (m / 2);
          Complex even = vector.getAt(evenIndex);
          Complex odd = vector.getAt(oddIndex);

          Complex wm = Complex.expI(-2d * Math.PI * k / (double) m).multiply(odd);
          vector.setAt(evenIndex, even.plus(wm));
          vector.setAt(oddIndex, even.minus(wm));
        }
      }
    }
  }

  private int bitReversedIndex(int n, int bits) {
    int reversedN = n;
    int count = bits - 1;

    n >>= 1;
    while (n > 0) {
      reversedN = (reversedN << 1) | (n & 1);
      count--;
      n >>= 1;
    }

    return ((reversedN << count) & ((1 << bits) - 1));
  }

  private void bitReverseVector(CVector buffer, int bits) {
    for (int j = 1; j < buffer.getSize() / 2; j++) {
      int swapPos = bitReversedIndex(j, bits);
      Complex temp = buffer.getAt(j);
      buffer.setAt(j, buffer.getAt(swapPos));
      buffer.setAt(swapPos, temp);
    }
  }

  @Override
  public String getName() {
    return "Cooley-Tukey iterative";
  }

  @Override
  public String getDescription() {
    return "Cooley-Tukey algorithm implemented iteratively (input array must have power of 2 sizes)";
  }

}
