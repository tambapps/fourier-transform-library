package com.tambapps.fft4j.complex.vector

import com.tambapps.fft4j.complex.Complex
import java.lang.UnsupportedOperationException

class ImmutableCVector(elements: Array<Complex>) : ArrayCVector(elements) {

    override fun set(i: Int, value: Complex) {
        throw UnsupportedOperationException("Cannot modify an immutable vector")
    }

    companion object {

        fun of(vararg values: Complex): ImmutableCVector {
            return ImmutableCVector(arrayOf(*values))
        }
    }
}