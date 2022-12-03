package com.tambapps.fft4j;

/**
 * Interface allowing to create lambdas of fourier transform functions
 */
public interface FastFourierFunction {

  void transform(double[] inputRe, double[] inputIm, double[] outputRe, double[] outputIm);

}
