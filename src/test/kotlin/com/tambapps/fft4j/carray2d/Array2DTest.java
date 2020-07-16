package com.tambapps.fft4j.carray2d;

import com.tambapps.fft4j.complex.Complex;
import com.tambapps.fft4j.util.CVector;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Array2DTest {

  @Test
  public void rowColumnTests() {
    rowColumnTest(new CArray2D(3, 2));
    rowColumnTest(new CArray2D(10, 10));
  }

  private void rowColumnTest(CArray2D array) {
    int M = array.getM(), N = array.getN();

    for (int j = 0; j < N; j++) {
      for (int i = 0; i < M; i++) {
        array.set(i, j, Complex.of(i, j));
      }
    }

    for (int j = 0; j < N; j++) {
      CVector column = array.getColumn(j);
      for (int i = 0; i < M; i++) {
        assertEquals("Should be equal", Complex.of(i, j), column.getAt(i));
      }
    }

    for (int i = 0; i < M; i++) {
      CVector row = array.getRow(i);
      for (int j = 0; j < N; j++) {
        assertEquals("Should be equal", Complex.of(i, j), row.getAt(j));
      }
    }
  }

  @Test
  public void rowColumnSetTests() {
    rowColumnSetTest(new CArray2D(4, 7), Complex.of(2, 4), Complex.of(34, 10));
  }

  private void rowColumnSetTest(CArray2D array, Complex valueCol, Complex valueRow) {
    CVector column = array.getColumn(array.getN() / 2);
    for (int i = 0; i < column.getSize(); i++) {
      column.setAt(i, valueCol);
    }

    for (int j = 0; j < array.getM(); j++) {
      assertEquals("Should be equal", valueCol, array.get(j, array.getN() / 2));
    }

    CVector row = array.getRow(array.getM() / 2);
    for (int i = 0; i < row.getSize(); i++) {
      row.setAt(i, valueRow);
    }

    for (int j = 0; j < array.getN(); j++) {
      assertEquals("Should be equal", valueRow, array.get(array.getM() / 2, j));
    }
  }

}
