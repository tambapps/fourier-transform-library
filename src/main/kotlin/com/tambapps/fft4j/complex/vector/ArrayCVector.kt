package com.tambapps.fft4j.complex.vector

import com.tambapps.fft4j.complex.Complex

open class ArrayCVector constructor(private val elements: Array<Complex>): AbstractCVector() {

    constructor(size: Int): this(Array<Complex>(size) { Complex.ZERO })

    override fun get(i: Int): Complex {
        return elements[i]
    }

    override fun set(i: Int, value: Complex) {
        elements[i] = value
    }

    override val size: Int
        get() = elements.size

}
