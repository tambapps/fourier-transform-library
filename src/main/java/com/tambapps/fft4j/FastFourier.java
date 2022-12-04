package com.tambapps.fft4j;

/**
 * Interface allowing to create lambdas of fourier transform functions. FastFourier are only for 1D fouriers. If you
 * want to perform a 2D FFT, use FastFourier2d.
 */
public interface FastFourier {

  /**
   * Perform a fourier transform on the input arrays and store it into the output arrays
   *
   * @param inputRe  the real parts of the input
   * @param inputIm  the imaginary parts of the input
   * @param outputRe the real parts of the output
   * @param outputIm the imaginary parts of the output
   */
  void transform(double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm);

  /**
   * Perform a fourier transform on the arrays
   *
   * @param re the real parts
   * @param im the imaginary
   */
  default void transform(double[] re, double[] im) {
    double[] outputRe = new double[re.length];
    double[] outputIm = new double[re.length];
    transform(re, im, outputRe, outputIm);
    System.arraycopy(outputRe, 0, re, 0, re.length);
    System.arraycopy(outputIm, 0, im, 0, re.length);
  }

  /**
   * Perform a fourier inverse on the input signal and store it in the output signal
   *
   * @param input  the input signal
   * @param output the output signal
   */
  default void inverse(Signal input, Signal output) {
    inverse(input.getRe(), input.getIm(), output.getRe(), output.getIm());
  }

  /**
   * Perform a fourier inverse on the signal and return the result
   *
   * @param signal the input signal
   * @return the fourier inverse of the provided signal
   */
  default Signal inverse(Signal signal) {
    Signal output = new Signal(signal.getLength());
    inverse(signal.getRe(), signal.getIm(), output.getRe(), output.getIm());
    return output;
  }

  /**
   * Perform a fourier inverse on the input arrays and store it into the output arrays
   *
   * @param inputRe  the real parts of the input
   * @param inputIm  the imaginary parts of the input
   * @param outputRe the real parts of the output
   * @param outputIm the imaginary parts of the output
   */
  default void inverse(double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm) {
    System.arraycopy(inputRe, 0, outputRe, 0, inputRe.length);
    System.arraycopy(inputIm, 0, outputIm, 0, inputRe.length);
    inverse(outputRe, outputIm);
  }

  /**
   * Perform a fourier inverse on the arrays
   *
   * @param re the real parts
   * @param im the imaginary
   */
  default void inverse(double[] re, double[] im) {
    for (int i = 0; i < re.length; i++) {
      im[i] = -im[i];
    }

    transform(re, im);

    double iN = 1d / ((double) re.length);
    for (int i = 0; i < re.length; i++) {
      re[i] = re[i] * iN;
      im[i] = -im[i] * iN;
    }
  }

  /**
   * Perform a fourier transform on the signal and return the result
   *
   * @param signal the input signal
   * @return the fourier inverse of the provided signal
   */
  Signal transform(Signal signal);
}
