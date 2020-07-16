package com.tambapps.fft4j.complex.vector

import com.tambapps.fft4j.complex.Complex

/**
 * An abstract CVector. Useful to extends this class when creating a new CVector implementation
 */
abstract class AbstractCVector : CVector {

    fun checkedIndex(i: Int): Int {
        if (i < 0 || i >= size) {
            throw IndexOutOfBoundsException(
                    java.lang.String.format("Tried to access index %d of vector with size %d", i, size))
        }
        return i
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder().append("[ ")
        for (i in 0 until size) {
            stringBuilder.append(get(i))
            if (i < size - 1) {
                stringBuilder.append(", ")
            }
        }
        stringBuilder.append(" ]")
        return stringBuilder.toString()
    }

    override fun copy(): CVector {
        return ArrayCVector(copiedArray())
    }

    override fun immutableCopy(): CVector {
        return ImmutableCVector(copiedArray())
    }

    override fun copy(dest: CVector) {
        for (i in 0 until size) {
            dest[i] = get(i)
        }
    }

    private fun copiedArray(): Array<Complex> {
        return Array(size) { i -> get(i)}
    }

    override fun equals(other: Any?): Boolean {
        if (other !is CVector) {
            return false
        }
        val v = other
        if (v.size != size) {
            return false
        }
        for (i in 0 until size) {
            if (get(i) != v[i]) {
                return false
            }
        }
        return true
    }
}
