package com.tambapps.fft4j;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Signal2dTest {
  
  private static final double DELTA = 0.01;

  @Test
  public void rowColumnTests() {
    rowColumnTest(new Signal2d(3, 2));
    rowColumnTest(new Signal2d(10, 10));
  }

  @Test
  public void rowColumnSetTests() {
    rowColumnSetTest(new Signal2d(4, 7), 2, 4, 34, 10);
  }

  private void rowColumnSetTest(Signal2d array, double valueReCol, double valueImCol,
                                double valueReRow, double valueImRow) {
    ISignal column = array.getColumn(array.getN() / 2);
    for (int i = 0; i < column.getLength(); i++) {
      column.setAt(i, valueReCol, valueImCol);
    }

    for (int j = 0; j < array.getM(); j++) {
      assertEquals("Should be equal", valueReCol, array.getReAt(j, array.getN() / 2), DELTA);
      assertEquals("Should be equal", valueImCol, array.getImAt(j, array.getN() / 2), DELTA);
    }

    ISignal row = array.getRow(array.getM() / 2);
    for (int i = 0; i < row.getLength(); i++) {
      row.setAt(i, valueReRow, valueImRow);
    }

    for (int j = 0; j < array.getN(); j++) {
      assertEquals("Should be equal", valueReRow, array.getReAt(array.getM() / 2, j), DELTA);
      assertEquals("Should be equal", valueImRow, array.getImAt(array.getM() / 2, j), DELTA);
    }
  }

  private void rowColumnTest(Signal2d array) {
    int M = array.getM(), N = array.getN();

    for (int j = 0; j < N; j++) {
      for (int i = 0; i < M; i++) {
        array.setAt(i, j, i, j);
      }
    }

    for (int j = 0; j < N; j++) {
      ISignal column = array.getColumn(j);
      for (int i = 0; i < M; i++) {
        assertEquals("Should be equal", i, column.getReAt(i), DELTA);
        assertEquals("Should be equal", j, column.getImAt(i), DELTA);
      }
    }

    for (int i = 0; i < M; i++) {
      ISignal row = array.getRow(i);
      for (int j = 0; j < N; j++) {
        assertEquals("Should be equal", i, row.getReAt(j), DELTA);
        assertEquals("Should be equal", j, row.getImAt(j), DELTA);
      }
    }
  }
}
