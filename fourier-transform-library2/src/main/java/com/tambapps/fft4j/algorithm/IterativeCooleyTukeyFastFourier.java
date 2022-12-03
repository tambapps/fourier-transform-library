package com.tambapps.fft4j.algorithm;

import com.tambapps.fft4j.FastFourierTransform;
import com.tambapps.fft4j.Signal;

import static com.tambapps.fft4j.FastFourierUtils.checkSizes;
import static com.tambapps.fft4j.FastFourierUtils.get2Exponent;
import static com.tambapps.fft4j.FastFourierUtils.swap;

/**
 * Compute the 1D FFT in the given vector
 * with the iterative Cooley-Tukey algorithm
 * The computation is made in the given vector
 *
 * @see <a href="https://rosettacode.org/wiki/Fast_Fourier_transform#Java">rosetta code</a>
 */
public final class IterativeCooleyTukeyFastFourier implements FastFourierTransform {


  @Override
  public Signal transform(Signal inputSignal) {
    Signal outputSignal = new Signal(inputSignal.getLength());
    transform(inputSignal, outputSignal);
    return outputSignal;
  }

  public void transform(Signal inputSignal, Signal outputSignal) {
    transform(inputSignal.getRe(), inputSignal.getIm(), outputSignal.getRe(), outputSignal.getIm());
  }

  public void transform(double[] inputRe, double[] outputRe, double[] outputIm) {
    transform(inputRe, new double[inputRe.length], outputRe, outputIm);
  }

  @Override
  public void transform(double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm) {
    checkSizes(inputRe, inputIm, outputRe, outputIm);
    int n = inputRe.length;
    int bits = get2Exponent(n);
    System.arraycopy(inputRe, 0, outputRe, 0, n);
    System.arraycopy(inputIm, 0, outputIm, 0, n);
    bitReverseVector(outputRe, outputIm, bits);


    for (int m = 2; m <= n; m <<= 1) {
      for (int i = 0; i < n; i += m) {
        for (int k = 0; k < m / 2; k++) {
          int evenIndex = i + k;
          int oddIndex = i + k + (m / 2);
          double evenRe = outputRe[evenIndex];
          double evenIm = outputIm[evenIndex];


          double oddRe = outputRe[oddIndex];
          double oddIm = outputIm[oddIndex];

          // constructing expI
          double x = -2d * Math.PI * k / (double) m;
          double expXRe = Math.cos(x);
          double expXIm = Math.sin(x);

          // now multiplying exp with odd
          double wmRe = expXRe * oddRe - expXIm * oddIm;
          double wmIm = expXRe * oddIm + expXIm * oddRe;

          outputRe[evenIndex] = evenRe + wmRe;
          outputIm[evenIndex] = evenIm + wmIm;

          outputRe[oddIndex] = evenRe - wmRe;
          outputIm[oddIndex] = evenIm - wmIm;
        }
      }
    }
  }

  private void bitReverseVector(double[] re, double[] im, int bits) {
    for (int j = 1; j < re.length / 2; j++) {
      int swapPos = bitReversedIndex(j, bits);
      swap(re, im, j, swapPos);
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
}
