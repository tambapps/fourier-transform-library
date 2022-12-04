package com.tambapps.fft4j.task;

import com.tambapps.fft4j.FastFourier;
import com.tambapps.fft4j.ISignal;

public class FourierInverseTask extends AbstractFourierTask {

  public FourierInverseTask(ISignal signal, FastFourier fastFourier) {
    super(signal, fastFourier);
  }

  @Override
  protected void compute(FastFourier fastFourier,
                         double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm) {
    fastFourier.inverse(inputRe, inputIm, outputRe, outputIm);
  }
}
