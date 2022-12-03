package com.tambapps.fft4j.algorithm;

import com.tambapps.fft4j.FastFourierTransform;
import com.tambapps.fft4j.Signal;

/**
 * Compute the 1D FFT in the given vector
 * with the iterative Cooley-Tukey algorithm
 * The computation is made in the given vector
 * (not very precise for large 2D arrays)
 *
 * @see <a href="https://rosettacode.org/wiki/Fast_Fourier_transform#Java">rosetta code</a>
 */
public final class RecursiveCooleyTukeyFastFourier implements FastFourierTransform {
  

  public void transform(double[] inputRe, double[] outputRe, double[] outputIm) {
    transform(inputRe, new double[inputRe.length], outputRe, outputIm);
  }

  @Override
  public void transform(double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm) {
   transform(new Signal(inputRe, inputIm), new Signal(outputRe, outputIm));
  }

  public void transform(Signal inputSignal, Signal outputSignal) {
    Signal realOutput = transform(inputSignal);
    realOutput.copyInto(outputSignal);
  }

  @Override
  public Signal transform(Signal signal) {
    int N = signal.getLength();
    if (N <= 1) {
      return signal;
    }

    Signal evens = transform(evensCopy(signal));
    Signal odds = transform(oddsCopy(signal));

    Signal T = new Signal(N / 2);
    for (int i = 0; i < N / 2; i++) {
      // constructing expI
      double x = -2d * Math.PI * ((double) i) / ((double) N);
      double expXRe = Math.cos(x);
      double expXIm = Math.sin(x);

      // now multiplying exp with odd
      T.setReAt(i, expXRe * odds.getReAt(i) - expXIm * odds.getImAt(i));
      T.setImAt(i, expXRe * odds.getImAt(i) + expXIm * odds.getReAt(i));
    }

    Signal result = new Signal(N);
    for (int i = 0; i < N / 2; i++) {
      result.setReAt(i, evens.getReAt(i) + T.getReAt(i));
      result.setImAt(i, evens.getImAt(i) + T.getImAt(i));

      result.setReAt(i + N / 2, evens.getReAt(i) - T.getReAt(i));
      result.setImAt(i + N / 2, evens.getImAt(i) - T.getImAt(i));
    }
    return result;
  }

  private static Signal evensCopy(Signal signal) {
    int size = (signal.getLength() + 1) / 2;
    Signal copy = new Signal(size);
    int count = 0;
    for (int i = 0; i < signal.getLength(); i += 2) {
      copy.setReAt(count, signal.getReAt(i));
      copy.setImAt(count, signal.getImAt(i));
      count++;
    }
    return copy;
  }

  private static Signal oddsCopy(Signal signal) {
    int size = signal.getLength() / 2;

    Signal copy = new Signal(size);
    int count = 0;
    for (int i = 1; i < signal.getLength(); i += 2) {
      copy.setReAt(count, signal.getReAt(i));
      copy.setImAt(count, signal.getImAt(i));
      count++;
    }
    return copy;
  }

}
