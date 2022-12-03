package com.tambapps.fft4j.task;

import com.tambapps.fft4j.FastFourier;
import com.tambapps.fft4j.ISignal;

public class FourierTransformTask extends AbstractFourierTask {

  public FourierTransformTask(ISignal signal, FastFourier fastFourier) {
    super(signal, fastFourier);
  }

  @Override
  protected void compute(FastFourier fastFourier,
                         double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm) {
    fastFourier.transform(inputRe, inputIm, outputRe, outputIm);
  }
}
