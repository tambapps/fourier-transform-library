package com.tambapps.fft4j.algorithm;

import com.tambapps.fft4j.Signal;

import static com.tambapps.fft4j.algorithm.Utils.checkSizes;
import static com.tambapps.fft4j.algorithm.Utils.inverseEnd;
import static com.tambapps.fft4j.algorithm.Utils.inverseStart;

public final class BasicFastFourier {

  public static void inverse(Signal input, Signal output) {
    inverse(input.getRe(), input.getIm(), output.getRe(), output.getIm());
  }

  public static Signal inverse(Signal signal) {
    Signal output = new Signal(signal.getLength());
    inverse(signal.getRe(), signal.getIm(), output.getRe(), output.getIm());
    return output;
  }

  public static void inverse(double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm) {
    System.arraycopy(inputRe, 0, outputRe, 0, inputRe.length);
    System.arraycopy(inputIm, 0, outputIm, 0, inputRe.length);
    inverse(outputRe, outputIm);
  }

  public static void inverse(double[] re, double[] im) {
    inverseStart(re, im);
    transform(re, im);
    inverseEnd(re, im);
  }

  public static Signal transform(Signal inputSignal) {
    Signal outputSignal = new Signal(inputSignal.getLength());
    transform(inputSignal, outputSignal);
    return outputSignal;
  }

  public static void transform(Signal inputSignal, Signal outputSignal) {
    transform(inputSignal.getRe(), inputSignal.getIm(), outputSignal.getRe(), outputSignal.getIm());
  }

  public static void transform(double[] re, double[] im) {
    double[] outputRe = new double[re.length];
    double[] outputIm = new double[re.length];
    transform(re, im, outputRe, outputIm);
    System.arraycopy(outputRe, 0, re, 0, re.length);
    System.arraycopy(outputIm, 0, im, 0, re.length);
  }

  public static void transform(double[] inputRe, double[] outputRe, double[] outputIm) {
    transform(inputRe, new double[inputRe.length], outputRe, outputIm);
  }

    /**
     * Compute the fast fourier transform in a basic way, like in the basic formula
     *
     * @param inputRe the real parts of input
     * @param inputIm the imaginary parts of input
     * @param outputRe the real parts of output
     * @param outputIm the imaginary partss of output
     */
  public static void transform(double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm) {
    checkSizes(inputRe, inputIm, outputRe, outputIm);
    int size = inputRe.length;
    double N = size;
    double[] temp = new double[] {0.0, 0.0};
    double[] temp2 = new double[] {0.0, 0.0};

    for (int k = 0; k < size; k++) {
      outputRe[k] = 0d;
      outputIm[k] = 0d;
      for (int n = 0; n < size; n++) {

        temp[0] = inputRe[n];
        temp[1] = inputIm[n];

        // computing exp^I
        double x = -2d * Math.PI * ((double) k) * ((double) n) / N;
        temp2[0] = Math.cos(x);
        temp2[1] = Math.sin(x);

        outputRe[k] += temp[0] * temp2[0] - temp[1] * temp2[1];
        outputIm[k] += temp[0] * temp2[1] + temp[1] * temp2[0];
      }
    }
  }
}
