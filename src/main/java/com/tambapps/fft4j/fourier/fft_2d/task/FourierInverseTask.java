package com.tambapps.fft4j.fourier.fft_2d.task;

import com.tambapps.fft4j.complex.vector.CVector;
import com.tambapps.fft4j.fourier.fft_1d.FastFourierInverse;
import com.tambapps.fft4j.fourier.fft_1d.FastFourierTransform;
import com.tambapps.fft4j.fourier.fft_1d.FastFouriers;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class FourierInverseTask implements Runnable {

  private final CVector vector;
  private final FastFourierInverse inverse;

  public FourierInverseTask(CVector vector, FastFourierTransform algorithm) {
    this(vector, FastFouriers.inverse(algorithm));
  }

  @Override
  public void run() {
    inverse.compute(vector);
  }

}