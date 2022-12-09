
# Java Fourier Transform Library

This is a library for computing 1-2 dimensional Fourier Transform. It was written with Java 8, and should be compatible on Android.
**without any compile dependencies**. 

You can import it with maven.

If you are more familiar with the old implementation (version 1.0), consult the [legacy branch](https://github.com/tambapps/fourier-transform-library/tree/legacy)
```xml
<dependency>
  <groupId>com.tambapps.fft4j</groupId>
  <artifactId>fft4j</artifactId>
  <version>2.0</version>
</dependency>
```

## Fast Fourier Transform
Here is an example of a 1D fast fourier transform. There are several algorithms to perform FFT
You can see all of them on the FourierAlgorithms class.
```groovy
double[] inputRe = inputDataRe();
double[] inputIm = inputDataIm();

double[] outputRe = new double[inputRe.length];
double[] outputIm = new double[inputRe.length];

FastFouriers.BASIC.transform(inputRe, inputIm, outputRe, outputIm);

// consult result in outputRe and outputIm
```

You can also use a `Signal` which encapsulates a real and imaginary double arrays.

```groovy
Signal input = inputData();
Signal output = FastFouriers.BASIC.transform(input);
```

3 different Fast Fourier Algorithms were implemented

### Basic
This is a trivial implementation of a Fourier Transform using the basic Fourier Transform formula

### Recursive Cooley–Tukey

A recursive implementation of the [Cooley–Tukey FFT algorithm](https://en.wikipedia.org/wiki/Cooley%E2%80%93Tukey_FFT_algorithm)

### Iterative Cooley–Tukey

An iterative implementation of the [Cooley–Tukey FFT algorithm](https://en.wikipedia.org/wiki/Cooley%E2%80%93Tukey_FFT_algorithm)


## 2D Fast Fourier Transform
You can apply 2D FFT with a FastFourierTransformer2D. You can change the algorithm used by the transformer
to compute fft by setting the AlgorithmChooser.
```groovy
Signal2d signal2d = new Signal2d(M, N);
fill(signal2d);

FastFourier2d transformer2D = new FastFourier2d();
transformer2D.transform(signal2d);
// do some things with the fourier transform
transformer2D.inverse(signal2d);

// don't forget to shut it down as it uses an executor service
transformer2D.shutdown();
```

## Example use case
You can consult the [fft-image-processing repo](https://github.com/nelson888/fft-image-processing), an app that transforms images using FFT
![fft-image-processing-screenshot-1](https://raw.githubusercontent.com/nelson888/fft-image-processing/master/screenshots/sample1.png)

![fft-image-processing-screenshot-2](https://raw.githubusercontent.com/nelson888/fft-image-processing/master/screenshots/sample2.png)
## Web link

[Link](https://tambapps.github.io/fourier-transform-library/)
