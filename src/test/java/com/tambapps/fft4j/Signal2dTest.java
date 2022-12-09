package com.tambapps.fft4j;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
      assertEquals(valueReCol, array.getReAt(j, array.getN() / 2), DELTA, "Should be equal");
      assertEquals(valueImCol, array.getImAt(j, array.getN() / 2), DELTA, "Should be equal");
    }

    ISignal row = array.getRow(array.getM() / 2);
    for (int i = 0; i < row.getLength(); i++) {
      row.setAt(i, valueReRow, valueImRow);
    }

    for (int j = 0; j < array.getN(); j++) {
      assertEquals(valueReRow, array.getReAt(array.getM() / 2, j), DELTA, "Should be equal");
      assertEquals(valueImRow, array.getImAt(array.getM() / 2, j), DELTA, "Should be equal");
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
        assertEquals(i, column.getReAt(i), DELTA, "Should be equal");
        assertEquals(j, column.getImAt(i), DELTA, "Should be equal");
      }
    }

    for (int i = 0; i < M; i++) {
      ISignal row = array.getRow(i);
      for (int j = 0; j < N; j++) {
        assertEquals(i, row.getReAt(j), DELTA, "Should be equal");
        assertEquals(j, row.getImAt(j), DELTA, "Should be equal");
      }
    }
  }
}
