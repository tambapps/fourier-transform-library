# Java Fourier Transform Library

This is a library for computing 1-2 dimensional Fourier Transform

##Example
Here is an example of a 1D fast fourier transform. There are several algorithms to perform FFT
You can see all of them on the FourierAlgorithms class.
```java
CVector input = new ArrayCVector(N);
fill(input);
CVector result = new ArrayCVector(input.getSize());
CVectorUtils.copy(input, result);
FourierAlgorithms.BASIC.compute(result);
```

Here is an example of a 2D fast fourier transform. You can change the algorithm used by the transformer
to compute fft by setting the AlgorithmChooser.
```java
FastFourierTransformer2D transformer2D = new FastFourierTransformer2D(Executors.newFixedThreadPool(4));
CArray2D array2D = new CArray2D(N, N);
fillArray(array2D);

transformer2D.transform(array2D);


FFTUtils.changeCenter(array2D);
Filters.threshold(200, true).apply(array2D);
displayArray(array2D);

transformer2D.inverse(array2D);
displayArray(array2D);
```