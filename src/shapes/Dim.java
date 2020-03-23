package shapes;

/**
 * A representation of a dimension (width, height) for an object.
 */
public class Dim {
  private final int width;
  private final int height;

  /**
   * This is a constructor for a dimension given the width and height.
   *
   * @param width  This is the width of the object.
   * @param height This is the height of the object.
   * @throws IllegalArgumentException If the arguements are negative.
   */
  public Dim(int width, int height) {
    if (validPosition(width, height)) {
      this.width = width;
      this.height = height;
    } else {
      throw new IllegalArgumentException("Negative Dimension are not allowed.");
    }
  }

  /**
   * A helper to check the validity of the dimension inputs.
   *
   * @param width  Of the shape as int.
   * @param height Of the shape as int.
   * @return True if it is valid and not negative.
   */
  private boolean validPosition(int width, int height) {
    return ((width >= 0) && (height >= 0));
  }

  /**
   * Getter for width field.
   *
   * @return The value of the width.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Getter for height field.
   *
   * @return The value of the height.
   */
  public int getHeight() {
    return height;
  }
}
