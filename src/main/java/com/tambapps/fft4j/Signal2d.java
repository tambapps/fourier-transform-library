package com.tambapps.fft4j;

import lombok.AllArgsConstructor;

/**
 * A 2d signal
 */
public class Signal2d extends Signal {

  private final Column[] columns;
  private final Row[] rows;

  /**
   * Constructs a 2D Signal of M rows and N columns, with the provided real and imaginary parts
   *
   * @param M  the number of rows
   * @param N  the number of columns
   * @param re the real parts
   * @param im the imaginary parts
   */
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

  /**
   * Constructs a 2D Signal of M rows and N columns.
   *
   * @param M the number of rows
   * @param N the number of columns
   */
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

  /**
   * Get the real part of the number at the specified row and column
   *
   * @param row the row
   * @param col the column
   * @return the real part of the number at the specified row and column
   */
  public double getReAt(int row, int col) {
    return getReAt(getIndex(row, col));
  }

  /**
   * Get the imaginary part of the number at the specified row and column
   *
   * @param row the row
   * @param col the column
   * @return the imaginary part of the number at the specified row and column
   */
  public double getImAt(int row, int col) {
    return getImAt(getIndex(row, col));
  }

  /**
   * Set the number at the specified row and column
   *
   * @param row the row
   * @param col the column
   * @param re  the real part
   * @param im  the imaginary part
   */
  public void setAt(int row, int col, double re, double im) {
    int i = getIndex(row, col);
    setReAt(i, re);
    setImAt(i, im);
  }

  /**
   * Set the real part of the number at the specified row and column
   *
   * @param row   the row
   * @param col   the column
   * @param value the real part to set
   */
  public void setReAt(int row, int col, double value) {
    setReAt(getIndex(row, col), value);
  }

  /**
   * Set the imaginary part of the number at the specified row and column
   *
   * @param row   the row
   * @param col   the column
   * @param value the imaginary part to set
   */
  public void setImAt(int row, int col, double value) {
    setImAt(getIndex(row, col), value);
  }

  private int getIndex(int row, int col) {
    return row * getN() + col;
  }

  /**
   * Get the ith row in form of a {@link ISignal}
   *
   * @param i the index
   * @return the ith row
   */
  public ISignal getRow(int i) {
    return rows[i];
  }

  /**
   * Get the ith column in form of a {@link ISignal}
   *
   * @param i the index
   * @return the ith column
   */
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
