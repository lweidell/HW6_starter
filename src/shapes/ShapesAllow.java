package shapes;

/**
 * A enum representation for allowed shape types in the animator.
 */
public enum ShapesAllow {
  Rect("Rectangle"),
  Oval("Oval");
  private final String X;

  /**
   * Constructor for shapes allowed given its string name.
   *
   * @param s The name of the shape.
   */
  ShapesAllow(String s) {
    this.X = s;
  }

  /**
   * Overvide to string to give the name of the shape.
   *
   * @return The string of the shape name.
   * @throws IllegalArgumentException If a null shape is given since that isnt allowed in
   *                                  annimation.
   */
  @Override
  public String toString() {
    return this.X;
  }
}
