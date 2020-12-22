package com.tambapps.fft4j.complex.array2d;

import com.tambapps.fft4j.complex.Complex;
import com.tambapps.fft4j.complex.vector.AbstractCVector;
import com.tambapps.fft4j.complex.vector.CVector;

import java.util.Arrays;
import java.util.Objects;

/**
 * A complex 2-dimensional array of size M, N. <br>
 * M is the number of rows, N is the number of columns
 */
public class CArray2D {

  private final Column[] columns;
  private final Row[] rows;

  //we store the 2D array in a 1D array of size N * M
  private final Complex[] array;

  /**
   * Creates a 2D array of size M, N
   *
   * @param M the number of rows
   * @param N the number of columns
   */
  public CArray2D(int M, int N) {
    array = new Complex[M * N];
    Arrays.fill(array, Complex.ZERO);
    columns = new Column[N];
    rows = new Row[M];
    for (int i = 0; i < columns.length; i++) {
      columns[i] = new Column(i);
    }
    for (int i = 0; i < rows.length; i++) {
      rows[i] = new Row(i);
    }
  }

  /**
   * Creates a 2D array of size M, N with the given values.
   *
   * @param M      the number of rows
   * @param N      the number of columns
   * @param values the values
   */
  public CArray2D(int M, int N, Complex[] values) {
    if (values.length != M * N) {
      throw new IllegalArgumentException("Array should be of size N * M: " + M * N);
    }
    array = Arrays.copyOf(values, values.length);
    for (int i = 0; i < array.length; i++) {
      if (array[i] == null) {
        array[i] = Complex.ZERO;
      }
    }
    columns = new Column[N];
    rows = new Row[M];
    for (int i = 0; i < columns.length; i++) {
      columns[i] = new Column(i);
    }
    for (int i = 0; i < rows.length; i++) {
      rows[i] = new Row(i);
    }
  }

  /**
   * Get the complex number at the index [row][col]
   *
   * @param row the row
   * @param col the col
   * @return the complex at the given indexes
   */
  public Complex get(int row, int col) {
    checkIndex(row, col);
    return array[getIndex(row, col)];
  }

  /**
   * Get the i-th column.
   *
   * @param i the column index
   * @return the i-th column
   */
  public CVector getAt(int i) {
    return getRow(i);
  }

  /**
   * Get the complex number at the index i, a 1D index.
   * i = row * N + col
   *
   * @param i the 1d index
   * @return the complex at the given index
   */
  public Complex get(int i) {
    checkIndex(i);
    return array[i];
  }

  /**
   * Sets the complex at the index [row][col]
   *
   * @param row the row
   * @param col the col
   */
  public void set(int row, int col, Complex value) {
    checkIndex(row, col);
    array[getIndex(row, col)] = Objects.requireNonNull(value, "Cannot set a null value");
  }

  /**
   * Sets the complex number at the index i, a 1D index.
   * i = row * N + col
   *
   * @param i the column index
   */
  public void set(int i, Complex value) {
    checkIndex(i);
    array[i] = Objects.requireNonNull(value, "Cannot set a null value");
  }

  private void checkIndex(int row, int col) {
    if (row < 0 || row >= getM() || col < 0 || col >= getN()) {
      throw new IndexOutOfBoundsException(String
        .format("Tried to access index (%d, %d) of array of size (%d, %d)", row, col, getM(),
          getN()));
    }
  }

  private void checkIndex(int i) {
    if (i < 0 || i >= array.length) {
      throw new IndexOutOfBoundsException(
        String.format("Tried to access index %d of array of size %d", i, getM() * getN()));
    }
  }

  private int getIndex(int row, int col) {
    return row * getN() + col;
  }

  /**
   * Returns the number of rows
   *
   * @return the number of rows
   */
  public int getM() {
    return rows.length;
  }

  /**
   * Returns the number of columns
   *
   * @return the number of columns
   */
  public int getN() {
    return columns.length;
  }

  /**
   * Returns the row at the index i
   *
   * @param i the index
   * @return the i-th row
   */
  public CVector getRow(int i) {
    if (i >= rows.length) {
      throw new IndexOutOfBoundsException(
        String.format("Tried to access row %d of array of size (%d, %d)", i, getM(), getN()));
    }
    return rows[i];
  }

  /**
   * Returns the column at the index i
   *
   * @param i the index
   * @return the i-th column
   */
  public CVector getColumn(int i) {
    if (i >= columns.length) {
      throw new IndexOutOfBoundsException(
        String.format("Tried to access column %d of array of size (%d, %d)", i, getM(), getN()));
    }
    return columns[i];
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder().append("(")
      .append(getM()).append(", ").append(getN()).append(")\n");
    for (int i = 0; i < getN() * getM(); i++) {
      stringBuilder.append("(").append(get(i)).append(")\t");
      if ((i + 1) % getN() == 0) {
        stringBuilder.append("\n");
      }
    }
    return stringBuilder.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof CArray2D)) {
      return false;
    }
    CArray2D a = (CArray2D) o;
    if (a.getM() != getM() || a.getN() != getN()) {
      return false;
    }

    return Arrays.equals(array, a.array);
  }

  /**
   * Returns a copy of this array
   *
   * @return the copy
   */
  public CArray2D copy() {
    int size = getM() * getN();
    Complex[] copyArray = new Complex[size];
    System.arraycopy(array, 0, copyArray, 0, size);
    return new CArray2D(getM(), getN(), copyArray);
  }

  /**
   * Returns an immutable copy of this array
   *
   * @return the copy
   */
  public CArray2D immutableCopy() {
    int size = getM() * getN();
    Complex[] copyArray = new Complex[size];
    System.arraycopy(array, 0, copyArray, 0, size);
    return new ImmutableC2D(getM(), getN(), copyArray);
  }

  private static class ImmutableC2D extends CArray2D {

    ImmutableC2D(int M, int N, Complex[] values) {
      super(M, N, values);
    }

    @Override
    public void set(int i, Complex value) {
      throw new UnsupportedOperationException("Cannot modify values of immutable array");
    }

    @Override
    public void set(int row, int col, Complex value) {
      throw new UnsupportedOperationException("Cannot modify values of immutable array");
    }
  }


  private class Column extends AbstractCVector {

    private final int c;

    Column(int c) {
      this.c = c;
    }

    @Override
    public Complex getAt(int i) {
      return get(i, c);
    }

    @Override
    public void setAt(int i, Complex value) {
      set(i, c, value);
    }

    @Override
    public int getSize() {
      return getM();
    }
  }


  private class Row extends AbstractCVector {

    private final int r;

    Row(int r) {
      this.r = r;
    }

    @Override
    public Complex getAt(int i) {
      return get(r, i);
    }

    @Override
    public void setAt(int i, Complex value) {
      set(r, i, value);
    }

    @Override
    public int getSize() {
      return getN();
    }
  }
}
