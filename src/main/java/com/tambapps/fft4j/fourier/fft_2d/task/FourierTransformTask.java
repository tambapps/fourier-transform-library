package com.tambapps.fft4j.fourier.fft_2d.task;

import com.tambapps.fft4j.complex.vector.CVector;
import com.tambapps.fft4j.fourier.fft_1d.FastFourierInverse;
import com.tambapps.fft4j.fourier.fft_1d.FastFourierTransform;
import com.tambapps.fft4j.fourier.fft_1d.FastFouriers;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FourierTransformTask implements Runnable {

  private final CVector vector;
  private final FastFourierTransform algorithm;

  @Override
  public void run() {
    algorithm.compute(vector);
  }

}