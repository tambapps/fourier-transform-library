package com.tambapps.fft4j;

import com.tambapps.fft4j.algorithm.BasicFastFourier;

public class FastFourier {

  public static Signal transform(Signal inputSignal) {
    Signal outputSignal = new Signal(inputSignal.getLength());
    transform(inputSignal, outputSignal);
    return outputSignal;
  }

  public static void transform(Signal inputSignal, Signal outputSignal) {
    transform(inputSignal.re, inputSignal.im, outputSignal.re, outputSignal.im);
  }
  public static void transform(double[] inputRe, double[] outputRe, double[] outputIm) {
    transform(inputRe, new double[inputRe.length], outputRe, outputIm);
  }
  public static void transform(double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm) {
    // TODO add some custom logic when length is power of two, to pick the more optimized algorithm
    BasicFastFourier.transform(inputRe, inputIm, outputRe, outputIm);
  }
}
