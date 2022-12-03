package com.tambapps.fft4j.algorithm;

import com.tambapps.fft4j.FastFourier;
import com.tambapps.fft4j.FastFourierTransform;
import com.tambapps.fft4j.Signal;

public class BestFastFourier implements FastFourierTransform {
  @Override
  public void transform(double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm) {
    FastFourierTransform transform = Utils.is2Power(inputIm.length) ? FastFourier.ITERATIVE_COOLEY_TUKEY : FastFourier.BASIC;
    transform.transform(inputRe, inputIm, outputRe, outputIm);
  }

  @Override
  public Signal transform(Signal inputSignal) {
    Signal outputSignal = new Signal(inputSignal.getLength());
    transform(inputSignal.getRe(), inputSignal.getIm(), outputSignal.getRe(), outputSignal.getIm());
    return outputSignal;
  }
}
