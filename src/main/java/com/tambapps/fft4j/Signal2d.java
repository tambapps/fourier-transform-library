package com.tambapps.fft4j;

import lombok.AllArgsConstructor;

import java.util.Arrays;

public class Signal2d extends Signal {

  private final Column[] columns;
  private final Row[] rows;

  public Signal2d(int M, int N, double[] re, double[] im) {
    super(re, im);
    columns = new Column[N];
    rows = new Row[M];
    for (int i = 0; i < columns.length; i++) {
      columns[i] = new Column(i);
    }
    for (int i = 0; i < rows.length; i++) {
      rows[i] = new Row(i);
    }
  }

  public Signal2d(int M, int N) {
    super(M * N);
    columns = new Column[N];
    rows = new Row[M];
    for (int i = 0; i < columns.length; i++) {
      columns[i] = new Column(i);
    }
    for (int i = 0; i < rows.length; i++) {
      rows[i] = new Row(i);
    }
  }

  public double getReAt(int row, int col) {
    return getReAt(getIndex(row, col));
  }

  public double getImAt(int row, int col) {
    return getImAt(getIndex(row, col));
  }

  public void setAt(int row, int col, double re, double im) {
    int i = getIndex(row, col);
    setReAt(i, re);
    setImAt(i, im);
  }

  public void setReAt(int row, int col, double value) {
    setReAt(getIndex(row, col), value);
  }

  public void setImAt(int row, int col, double value) {
    setImAt(getIndex(row, col), value);
  }

  private int getIndex(int row, int col) {
    return row * getN() + col;
  }

  public ISignal getRow(int i) {
    return rows[i];
  }

  public ISignal getColumn(int i) {
    return columns[i];
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Signal2d signal2d = (Signal2d) o;
    return getM() == signal2d.getM() && getN() == signal2d.getN();
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + getM();
    result = 31 * result + getN();
    return result;
  }

  @AllArgsConstructor
  private class Column implements ISignal {

    private final int c;

    @Override
    public double getReAt(int i) {
      return Signal2d.this.getReAt(i, c);
    }

    @Override
    public double getImAt(int i) {
      return Signal2d.this.getImAt(i, c);
    }

    @Override
    public void setReAt(int i, double value) {
      Signal2d.this.setReAt(i, c, value);
    }

    @Override
    public void setImAt(int i, double value) {
      Signal2d.this.setImAt(i, c, value);
    }

    @Override
    public int getLength() {
      return getM();
    }
  }

  @AllArgsConstructor
  private class Row implements ISignal {

    private final int r;

    @Override
    public double getReAt(int i) {
      return Signal2d.this.getReAt(r, i);
    }

    @Override
    public double getImAt(int i) {
      return Signal2d.this.getImAt(r, i);
    }

    @Override
    public void setReAt(int i, double value) {
      Signal2d.this.setReAt(r, i, value);
    }

    @Override
    public void setImAt(int i, double value) {
      Signal2d.this.setImAt(r, i, value);
    }

    @Override
    public int getLength() {
      return getN();
    }
  }
}
