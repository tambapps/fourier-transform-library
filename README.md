
# Java Fourier Transform Library

This is a library for computing 1-2 dimensional Fourier Transform. It was written with Java 8 (re-writting is ongoing)
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

TODO update README
## 1D Fast Fourier Transform
Here is an example of a 1D fast fourier transform. There are several algorithms to perform FFT
You can see all of them on the FourierAlgorithms class.
```groovy
CVector input = new ArrayCVector(N);
fill(input); //fill the array with whatever you like
CVector result = new ArrayCVector(input.getSize());
CVectorUtils.copy(input, result);
FourierAlgorithms.BASIC.compute(result);
```

## 2D Fast Fourier Transform
You can apply 2D FFT with a FastFourierTransformer2D. You can change the algorithm used by the transformer
to compute fft by setting the AlgorithmChooser.
```groovy
FastFourierTransformer2D transformer2D = new FastFourierTransformer2D(Executors.newFixedThreadPool(4));
CArray2D array2D = new CArray2D(N, N);
fillArray(array2D);

transformer2D.transform(array2D);

FFTUtils.changeCenter(array2D);
Filters.threshold(200, true).apply(array2D);
displayArray(array2D); //display with whatever framework the array2D

transformer2D.inverse(array2D);
displayArray(array2D);
```
## Filters
There are many filters implemented in the `Filters` class. If you want to implement your own, just extends `AbstractFilter`.


## Change center
If you want to display an FFT, it can be useful to change the center. There is the function `FFTUtils.changeCenter(array)` for that.

## Groovy friendly
the CArray2D and other classes have functions that define operator in groovy. Here is an example of groovy code

```groovy
def vector = new CVector(N)
init(vector)
FourierAlgorithms.BASIC(vector)
display(vector)
for(int i = 0; i < vector.size ; i++) {
  println vector[i]
}
```

```groovy
def array = new ArrayCVector(N, M)
init(array)
for(int i = 0; i < array.M ; i++) {
  for(int j = 0; i < array.N ; i++) {
    println array[i][j]
  }
}
display(array)
def recFilter = Filters.rectangle(size, size, true)
recFilter(array)
display(array)
```

## Example use case
You can consult the [fft-image-processing repo](https://github.com/nelson888/fft-image-processing), an app that transforms images using FFT
![fft-image-processing-screenshot-1](https://raw.githubusercontent.com/nelson888/fft-image-processing/master/screenshots/sample1.png)

![fft-image-processing-screenshot-2](https://raw.githubusercontent.com/nelson888/fft-image-processing/master/screenshots/sample2.png)
## Web link

[Link](https://tambapps.github.io/fourier-transform-library/)
