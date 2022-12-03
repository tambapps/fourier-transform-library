package com.tambapps.fft4j.task;

import com.tambapps.fft4j.FastFourierTransform;
import com.tambapps.fft4j.ISignal;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FourierTask implements Runnable {

  private final ISignal signal;
  private final FastFourierTransform algorithm;

  @Override
  public void run() {
    // TODO
  }
}
