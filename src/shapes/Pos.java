package shapes;

/**
 * A representation for a position (x, y) value.
 */
public class Pos {
  private final int x;
  private final int y;

  /**
   * This is a constructor to make a shape with a given x and y cord.
   *
   * @param x This is the horizontal cordinate.
   * @param y This is the vertical cordinate.
   * @throws IllegalArgumentException If the position cords are negative.
   */
  public Pos(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Getter for x-cordinate field.
   *
   * @return The value of the x cord.
   */
  public int getX() {
    return x;
  }

  /**
   * Getter for y-cordinate field.
   *
   * @return The value of the y cord.
   */
  public int getY() {
    return y;
  }
}
