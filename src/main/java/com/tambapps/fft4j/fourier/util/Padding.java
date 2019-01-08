package com.tambapps.fft4j.fourier.util;

/**
 * Representation of a padding.
 * zero-padding => raise resolution
 */
public class Padding {
  public static final Padding ZERO = new Padding(0, 0, 0, 0);

  private final int left;
  private final int right;
  private final int top;
  private final int end;

  /**
   * constructs a padding with the given value
   *
   * @param left  left padding
   * @param right right padding
   * @param top   top padding
   * @param end   end/bottom padding
   */
  public Padding(int left, int right, int top, int end) {
    this.left = left;
    this.right = right;
    this.top = top;
    this.end = end;
  }

  /**
   * Returns the left padding
   *
   * @return left padding
   */
  public int getLeft() {
    return left;
  }

  /**
   * Returns the right padding
   *
   * @return right padding
   */
  public int getRight() {
    return right;
  }

  /**
   * Returns the top padding
   *
   * @return top padding
   */
  public int getTop() {
    return top;
  }

  /**
   * Returns the end padding
   *
   * @return end padding
   */
  public int getEnd() {
    return end;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Padding padding = (Padding) o;
    return left == padding.left &&
      right == padding.right &&
      top == padding.top &&
      end == padding.end;
  }
}
