package com.tambapps.fft4j.complex.array2d

import com.tambapps.fft4j.complex.Complex
import com.tambapps.fft4j.complex.vector.CVector
import junit.framework.Assert.assertEquals
import org.junit.Test

class CArray2DTest {

    @Test
    fun rowColumnTests() {
        rowColumnTest(CArray2D(3, 2))
        rowColumnTest(CArray2D(10, 10))
    }

    private fun rowColumnTest(array: CArray2D) {
        val M: Int = array.m
        val N: Int = array.n
        for (j in 0 until N) {
            for (i in 0 until M) {
                array[i, j] = Complex.of(i.toDouble(), j.toDouble())
            }
        }
        for (j in 0 until N) {
            val column: CVector = array.getColumn(j)
            for (i in 0 until M) {
                assertEquals("Should be equal", Complex.of(i.toDouble(), j.toDouble()), column[i])
            }
        }
        for (i in 0 until M) {
            val row: CVector = array.getRow(i)
            for (j in 0 until N) {
                assertEquals("Should be equal", Complex.of(i.toDouble(), j.toDouble()), row[j])
            }
        }
    }

    @Test
    fun rowColumnSetTests() {
        rowColumnSetTest(CArray2D(4, 7), Complex.of(2.0, 4.0), Complex.of(34.0, 10.0))
    }

    private fun rowColumnSetTest(array: CArray2D, valueCol: Complex, valueRow: Complex) {
        val column: CVector = array.getColumn(array.n / 2)
        for (i in 0 until column.size) {
            column[i] = valueCol
        }
        for (j in 0 until array.m) {
            assertEquals("Should be equal", valueCol, array[j, array.n / 2])
        }
        val row: CVector = array.getRow(array.m / 2)
        for (i in 0 until row.size) {
            row[i] = valueRow
        }
        for (j in 0 until array.n) {
            assertEquals("Should be equal", valueRow, array[array.m / 2, j])
        }
    }
}
