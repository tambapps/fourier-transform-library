package com.tambapps.fft4j.fourier.fft_1d;

import com.tambapps.fft4j.complex.Complex;
import com.tambapps.fft4j.fourier.util.Utils;
import com.tambapps.fft4j.util.ArrayCVector;
import com.tambapps.fft4j.util.CVector;

/**
 * Class that implements multiple 1D FFT algorithms
 */
public final class FourierAlgorithms {

  /**
   * A basic implementation of the FFT <br>
   * Implemented the computation like in the basic formula
   */
  public static final FastFourierTransform BASIC = new BasicFFT();
  /**
   * Cooley-Tukey algorithm implemented recursively. <br>
   * Input size must be a power of two
   */
  public static final FastFourierTransform CT_RECURSIVE = new CooleyTukeyRecursiveFFT();

  /**
   * Cooley-Tukey algorithm implemented iteratively. <br>
   * Input size must be a power of two
   */
  public static final FastFourierTransform CT_ITERATIVE = new CooleyTukeyIterativeFFT();

  /**
   * Inverse algorithm. The inverse algorithm use a FFT algorithm given
   * as a parameter.
   */
  public static FastFourierInverse inverse(FastFourierTransform algorithm) {
    return new FastFourierInverseImpl(algorithm);
  }

  private FourierAlgorithms() {}

  /**
   * The basic algorithm for the FFT
   *
   */
  private static class BasicFFT extends AbstractFFTAlgorithm {

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

  /**
   * Compute the 1D FFT in the given vector
   * with the iterative Cooley-Tukey algorithm
   * The computation is made in the given vector
   * (not very precise for large 2D arrays)
   *
   * @link from https://rosettacode.org/wiki/Fast_Fourier_transform#Java
   */
  private static class CooleyTukeyIterativeFFT extends AbstractFFTAlgorithm {

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

  /**
   * Compute the FFT in the given vector
   * with the recursive Cooley-Tukey algorithm
   *
   * @link from https://rosettacode.org/wiki/Fast_Fourier_transform
   */
  private static class CooleyTukeyRecursiveFFT extends AbstractFFTAlgorithm {

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

  private abstract static class AbstractFFTAlgorithm implements FastFourierTransform {
    @Override
    public void call(CVector vector) {
      compute(vector);
    }

    @Override
    public CVector computeCopy(CVector vector) {
      CVector result = vector.copy();
      compute(result);
      return result;
    }
  }

  /**
   * Inverse a Fourier Transformed vector using a given FFT algorithm
   *
   */
  private static class FastFourierInverseImpl implements FastFourierInverse {
    private final FastFourierTransform algorithm;

    private FastFourierInverseImpl(FastFourierTransform algorithm) {
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
}
