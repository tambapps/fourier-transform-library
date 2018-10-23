package com.tambapps.math.util;

import com.tambapps.math.complex.Complex;

public interface CVector {

  Complex getAt(int i);

  void setAt(int i, Complex value);

  int getSize();

}
