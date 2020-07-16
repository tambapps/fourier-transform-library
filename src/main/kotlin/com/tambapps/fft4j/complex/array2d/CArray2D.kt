package com.tambapps.fft4j.complex.array2d

import com.tambapps.fft4j.complex.Complex
import com.tambapps.fft4j.complex.vector.AbstractCVector
import com.tambapps.fft4j.complex.vector.CVector

/**
 * A complex 2-dimensional array of size M, N. <br></br>
 * M is the number of rows, N is the number of columns
 */
open class CArray2D {
    private val columns: Array<Column>
    private val rows: Array<Row>
    //we store the 2D array in a 1D array of size N * M
    private val array: Array<Complex>

    /**
     * Creates a 2D array of size M, N
     *
     * @param M the number of rows
     * @param N the number of columns
     */
    constructor(M: Int, N: Int) {
        array = Array(M * N) { Complex.ZERO }
        columns = Array(N) { i -> Column(i) }
        rows = Array(M) { i -> Row(i) }
    }

    /**
     * Creates a 2D array of size M, N with the given values.
     *
     * @param M      the number of rows
     * @param N      the number of columns
     * @param values the values
     */
    constructor(M: Int, N: Int, values: Array<Complex>) {
        require(values.size == M * N) { "Array should be of size N * M: " + M * N }
        array = values.copyOf()
        columns = Array(N) { i -> Column(i) }
        rows = Array(M) { i -> Row(i) }
    }

    /**
     * Get the complex number at the index [row][col]
     *
     * @param row the row
     * @param col the col
     * @return the complex at the given indexes
     */
    operator fun get(row: Int, col: Int): Complex {
        checkIndex(row, col)
        return array[getIndex(row, col)]
    }

    /**
     * Get the complex number at the index i, a 1D index.
     * i = row * N + col
     *
     * @param i the 1d index
     * @return the complex at the given index
     */
    operator fun get(i: Int): Complex {
        checkIndex(i)
        return array[i]
    }

    /**
     * Sets the complex at the index [row][col]
     *
     * @param row the row
     * @param col the col
     */
    open operator fun set(row: Int, col: Int, value: Complex) {
        checkIndex(row, col)
        array[getIndex(row, col)] = value
    }

    /**
     * Sets the complex number at the index i, a 1D index.
     * i = row * N + col
     *
     * @param i the column index
     */
    open operator fun set(i: Int, value: Complex) {
        checkIndex(i)
        array[i] = value
    }

    private fun checkIndex(row: Int, col: Int) {
        if (row < 0 || row >= m || col < 0 || col >= n) {
            throw IndexOutOfBoundsException(String.format("Tried to access index (%d, %d) of array of size (%d, %d)", row, col, m,
                    n))
        }
    }

    private fun checkIndex(i: Int) {
        if (i < 0 || i >= array.size) {
            throw IndexOutOfBoundsException(String.format("Tried to access index %d of array of size %d", i, m * n))
        }
    }

    private fun getIndex(row: Int, col: Int): Int {
        return row * n + col
    }

    /**
     * Returns the number of rows
     *
     * @return the number of rows
     */
    val m: Int
        get() = rows.size

    /**
     * Returns the number of columns
     *
     * @return the number of columns
     */
    val n: Int
        get() = columns.size

    /**
     * Returns the row at the index i
     *
     * @param i the index
     * @return the i-th row
     */
    fun getRow(i: Int): CVector {
        if (i >= rows.size) {
            throw IndexOutOfBoundsException(String.format("Tried to access row %d of array of size (%d, %d)", i, m, n))
        }
        return rows[i]
    }

    /**
     * Returns the column at the index i
     *
     * @param i the index
     * @return the i-th column
     */
    fun getColumn(i: Int): CVector {
        if (i >= columns.size) {
            throw IndexOutOfBoundsException(String.format("Tried to access column %d of array of size (%d, %d)", i, m, n))
        }
        return columns[i]
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder().append("(")
                .append(m).append(", ").append(n).append(")\n")
        for (i in 0 until n * m) {
            stringBuilder.append("(").append(get(i)).append(")\t")
            if ((i + 1) % n == 0) {
                stringBuilder.append("\n")
            }
        }
        return stringBuilder.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is CArray2D) {
            return false
        }
        val a = other
        return if (a.m != m || a.n != n) {
            false
        } else array.contentEquals(a.array)
    }

    override fun hashCode(): Int {
        return array.contentHashCode()
    }

    /**  override fun hashCode(): Int {
            TODO("not implemented")
        }
     * Returns a copy of this array
     *
     * @return the copy
     */
    fun copy(): CArray2D {
        return CArray2D(m, n, array.copyOf())
    }

    /**
     * Returns an immutable copy of this array
     *
     * @return the copy
     */
    fun immutableCopy(): CArray2D {
        return ImmutableC2D(m, n, array.copyOf())
    }

    private class ImmutableC2D internal constructor(M: Int, N: Int, values: Array<Complex>) : CArray2D(M, N, values) {
        override fun set(i: Int, value: Complex) {
            throw UnsupportedOperationException("Cannot modify values of immutable array")
        }

        override fun set(row: Int, col: Int, value: Complex) {
            throw UnsupportedOperationException("Cannot modify values of immutable array")
        }
    }

    private inner class Column internal constructor(val c: Int) : AbstractCVector() {

        override operator fun get(i: Int): Complex {
            return get(i, c)
        }


        override operator fun set(i: Int, value: Complex) {
            set(i, c, value)
        }

        override val size: Int
            get() = m

    }

    private inner class Row internal constructor(private val r: Int) : AbstractCVector() {

        override operator fun get(i: Int): Complex {
            return get(r, i)
        }

        override operator fun set(i: Int, value: Complex) {
            set(r, i, value)
        }

        override val size: Int
            get() = n

    }
}
