package com.tambapps.math.fourier.filtering;

import com.tambapps.math.complex.Complex;

/**
 * A threshold filter. Let in/out, values that are lower a given threshold
 */
class ThresholdFilter extends AbstractFilter {

    private final double threshold;

    ThresholdFilter(double threshold) {
        this.threshold = threshold;
    }

    @Override
    Complex apply(Complex c, int i, int j, int M, int N) {
        return c.abs() < threshold ? c : Complex.ZERO;
    }
}
