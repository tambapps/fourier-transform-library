package com.tambapps.fft4j.algorithm;

import com.tambapps.fft4j.FastFouriers;
import com.tambapps.fft4j.FastFourier;
import com.tambapps.fft4j.Signal;
import com.tambapps.fft4j.FastFourierUtils;

public class BestFastFourier implements FastFourier {
  @Override
  public void transform(double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm) {
    FastFourier transform = FastFourierUtils.is2Power(inputIm.length) ? FastFouriers.ITERATIVE_COOLEY_TUKEY : FastFouriers.BASIC;
    transform.transform(inputRe, inputIm, outputRe, outputIm);
  }

  @Override
  public Signal transform(Signal inputSignal) {
    Signal outputSignal = new Signal(inputSignal.getLength());
    transform(inputSignal.getRe(), inputSignal.getIm(), outputSignal.getRe(), outputSignal.getIm());
    return outputSignal;
  }
}
