package com.tambapps.math.array_2d;

import com.tambapps.math.complex.Complex;
import com.tambapps.math.util.AbstractCVector;
import com.tambapps.math.util.CVector;

import java.util.Arrays;

public class CArray2D {

  //M = nb of rows (= column size)
  //N = nb of columns (= row size)
  private final Column[] columns;
  private final Row[] rows;

  //we store the 2D array in a 1D array of size N * M
  private final Complex[] array;

  public CArray2D(int M, int N) {
    this(M, N, new Complex[M * N]);
  }

  public CArray2D(int M, int N, Complex[] values) {
    if (values.length != M * N) {
      throw new IllegalArgumentException("Array should be of size N * M: " + M * N);
    }
    array = values;
    columns = new Column[N];
    rows = new Row[M];
    for (int i = 0; i < columns.length; i++) {
      columns[i] = new Column(i);
    }
    for (int i = 0; i < rows.length; i++) {
      rows[i] = new Row(i);
    }
  }

  public Complex get(int row, int col) {
    checkIndex(row, col);
    return array[getIndex(row, col)];
  }

  CVector getAt(int i) {
    if (i >= rows.length) {
      throw new IndexOutOfBoundsException(
          String.format("Tried to access row (%d) of array of size (%d, %d)", i, getM(), getN()));
    }
    return rows[i];
  }

  public Complex get(int i) {
    checkIndex(i);
    return array[i];
  }

  public void set(int row, int col, Complex value) {
    checkIndex(row, col);
    array[getIndex(row, col)] = value;
  }

  public void set(int i, Complex value) {
    checkIndex(i);
    array[i] = value;
  }

  private void checkIndex(int row, int col) {
    if (row < 0 || row  >= getM() || col < 0 || col >= getN()) {
      throw new IndexOutOfBoundsException(String
          .format("Tried to access index (%d, %d) of array of size (%d, %d)", row, col, getM(), getN()));
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

  public int getM() {
    return rows.length;
  }

  public int getN() {
    return columns.length;
  }

  public Row getRow(int i) {
    return rows[i];
  }

  public Column getColumn(int i) {
    return columns[i];
  }

  private class Column extends AbstractCVector {
    final int c;

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
    private int r;

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
    if (! (o instanceof CArray2D)) {
      return false;
    }
    CArray2D a = (CArray2D) o;
    if (a.getM() != getM() || a.getN() != getN()) {
      return false;
    }

    return Arrays.equals(array, a.array);
  }

  public CArray2D copy() {
    int size = getM() * getN();
    Complex[] copyArray = new Complex[size];
    System.arraycopy(array, 0, copyArray, 0, size);
    return new CArray2D(getM(), getN(), copyArray);
  }

  public CArray2D immutableCopy() {
    int size = getM() * getN();
    Complex[] copyArray = new Complex[size];
    System.arraycopy(array, 0, copyArray, 0, size);
    return new ImmutableC2D(getM(), getN(), copyArray);
  }

  static class ImmutableC2D extends CArray2D {

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
}
