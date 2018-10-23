package com.tambapps.math.fourier.filtering;

import com.tambapps.math.complex.Complex;

/**
 * A circle filter. Let in/out, values that are inside a centered circle
 */
class CircleFilter extends AbstractFilter {

    private final int radius2;
    private final boolean reverted;
    private int cM;
    private int cN;

    CircleFilter(int radius, boolean reverted) {
        this.radius2 = pow2(radius);
        this.reverted = reverted;
    }

    @Override
    void before(int M, int N) {
        cM = M / 2;
        cN = N / 2;
    }

    @Override
    Complex apply(Complex c, int i, int j, int M, int N) {
        int distance2 =  pow2(i - cM) + pow2(j - cN);
        if (distance2 < radius2) {
            return reverted ? c : Complex.ZERO;
        }
        return reverted ? Complex.ZERO : c;
    }

    private int pow2(int i) {
        return  i * i;
    }
}
