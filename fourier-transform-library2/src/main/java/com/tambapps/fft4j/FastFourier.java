package com.tambapps.fft4j;

/**
 * Interface allowing to create lambdas of fourier transform functions
 */
public interface FastFourier {

  void transform(double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm);

  default void transform(double[] re, double[] im) {
    double[] outputRe = new double[re.length];
    double[] outputIm = new double[re.length];
    transform(re, im, outputRe, outputIm);
    System.arraycopy(outputRe, 0, re, 0, re.length);
    System.arraycopy(outputIm, 0, im, 0, re.length);
  }

  default void inverse(Signal input, Signal output) {
    inverse(input.getRe(), input.getIm(), output.getRe(), output.getIm());
  }

  default Signal inverse(Signal signal) {
    Signal output = new Signal(signal.getLength());
    inverse(signal.getRe(), signal.getIm(), output.getRe(), output.getIm());
    return output;
  }

  default void inverse(double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm) {
    System.arraycopy(inputRe, 0, outputRe, 0, inputRe.length);
    System.arraycopy(inputIm, 0, outputIm, 0, inputRe.length);
    inverse(outputRe, outputIm);
  }

  default void inverse(double[] re, double[] im) {
    for (int i = 0; i < re.length; i++) {
      im[i] = - im[i];
    }

    transform(re, im);

    double iN = 1d / ((double) re.length);
    for (int i = 0; i < re.length; i++) {
      re[i] = re[i] * iN;
      im[i] = -im[i] * iN;
    }
  }

  Signal transform(Signal signal);
}
