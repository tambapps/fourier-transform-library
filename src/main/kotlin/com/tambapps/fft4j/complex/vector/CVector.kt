package com.tambapps.fft4j.complex.vector

import com.tambapps.fft4j.complex.Complex


/**
 * Vector holding complex numbers
 */
interface CVector {
    /**
     * Returns the complex at the index i
     *
     * @param i the index
     * @return the i-th complex
     */
    operator fun get(i: Int): Complex

    /**
     * Sets the complex at the index o
     *
     * @param i     the index
     * @param value the value to set
     */
    operator fun set(i: Int, value: Complex)

    /**
     * Returns the size of the vector
     *
     * @return the size of the vector
     */
    val size: Int

    /**
     * returns a copy of this vector
     *
     * @return a copy of this vector
     */
    fun copy(): CVector

    /**
     * returns an immutable copy of this vector
     *
     * @return an immutable copy of this vector
     */
    fun immutableCopy(): CVector

    fun copy(dest: CVector)
}
