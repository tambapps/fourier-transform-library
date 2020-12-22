package com.tambapps.fft4j.complex.vector;

import com.tambapps.fft4j.complex.Complex;

import java.util.Objects;

/**
 * An abstract CVector. Useful to extends this class when creating a new CVector implementation
 */
public abstract class AbstractCVector implements CVector {

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof CVector)) {
      return false;
    }
    CVector v = (CVector) o;
    if (v.getSize() != getSize()) {
      return false;
    }

    for (int i = 0; i < getSize(); i++) {
      if (!Objects.equals(getAt(i), v.getAt(i))) {
        return false;
      }
    }
    return true;
  }

  int checkedIndex(int i) {
    if (i < 0 || i >= getSize()) {
      throw new IndexOutOfBoundsException(
        String.format("Tried to access index %d of vector with size %d", i, getSize()));
    }
    return i;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder().append("[ ");
    for (int i = 0; i < getSize(); i++) {
      stringBuilder.append(getAt(i));
      if (i < getSize() - 1) {
        stringBuilder.append(", ");
      }
    }
    stringBuilder.append(" ]");
    return stringBuilder.toString();
  }

  @Override
  public CVector copy() {
    return new ArrayCVector(copiedArray());
  }

  @Override
  public CVector immutableCopy() {
    return new ImmutableCVector(copiedArray());
  }

  @Override
  public void copy(CVector dest) {
    for (int i = 0; i < getSize(); i++) {
      dest.setAt(i, getAt(i));
    }
  }

  private Complex[] copiedArray() {
    Complex[] complexes = new Complex[getSize()];
    for (int i = 0; i < complexes.length; i++) {
      complexes[i] = getAt(i);
    }
    return complexes;
  }
}
