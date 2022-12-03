package com.tambapps.fft4j.task;

import com.tambapps.fft4j.FastFourierTransform;
import com.tambapps.fft4j.ISignal;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractFourierTask implements Runnable {

  private final ISignal signal;
  private final FastFourierTransform fastFourier;

  @Override
  public void run() {
    double[] inputRe = new double[signal.getLength()];
    double[] inputIm = new double[signal.getLength()];

    double[] outputRe = new double[signal.getLength()];
    double[] outputIm = new double[signal.getLength()];

    signal.copyInto(inputRe, inputIm);
    compute(fastFourier, inputRe, inputIm, outputRe, outputIm);
    signal.setFrom(outputRe, outputIm);
  }

  protected abstract void compute(FastFourierTransform fastFourier, double[] inputRe, double[] inputIm,
                                    double[] outputRe, double[] outputIm);
}
