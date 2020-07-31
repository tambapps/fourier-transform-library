# Kotlin Fourier Transform Library

This is a library for computing 1-2 dimensional Fourier Transform. It was written with Kotlin but
you can use it in a Java project

You can import it with maven.

```xml
<dependency>
  <groupId>com.tambapps.fft4j</groupId>
  <artifactId>fft4j</artifactId>
  <version>1.0</version>
</dependency>
```
## 1D Fast Fourier Transform
Here is an example of a 1D fast fourier transform. There are several algorithms to perform FFT
You can see all of them on the FourierAlgorithms class.
```kotlin
val input = ArrayCVector(N)
fill(input) //fill the array with whatever you like
val result = ArrayCVector(input.size)
val result = FourierAlgorithms.BASIC.compute(input)
```

## 2D Fast Fourier Transform
You can apply 2D FFT with a FastFourierTransformer2D. You can change the algorithm used by the transformer
to compute fft by setting the AlgorithmChooser.
```kotlin
val transformer2D = FastFourierTransformer2D()
val array2D = CArray2D(N, N)
fillArray(array2D)

transformer2D.transform(array2D)

FFTUtils.changeCenter(array2D)
Filters.threshold(200, true).apply(array2D)
displayArray(array2D) //display with whatever framework the array2D

transformer2D.inverse(array2D)
displayArray(array2D)
```
## Filters
There are many filters implemented in the `Filters` class. If you want to implement your own, just extends `AbstractFilter`.


## Change center
If you want to display an FFT, it can be useful to change the center. There is the function `FFTUtils.changeCenter(array)` for that.


## Web link

[Link](https://tambapps.github.io/fourier-transform-library/)
