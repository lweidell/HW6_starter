import org.junit.Test;

import shapes.Pos;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for position representations.
 */
public class PosTest {
  Pos test1 = new Pos(10, 20);
  Pos zeroWidth = new Pos(0, 1000);
  Pos zeroHeight = new Pos(10000, 0);
  Pos negX = new Pos(-1000, 10);
  Pos negBoth = new Pos(-500, -500);
  Pos negY = new Pos(0, -1000);

  @Test
  public void getX() {
    assertEquals(10, test1.getX());
    assertEquals(0, zeroWidth.getX());
    assertEquals(10000, zeroHeight.getX());
    assertEquals(-1000, negX.getX());
    assertEquals(-500, negBoth.getX());
    assertEquals(0, negY.getX());

  }

  @Test
  public void getY() {
    assertEquals(20, test1.getY());
    assertEquals(1000, zeroWidth.getY());
    assertEquals(0, zeroHeight.getY());
    assertEquals(10, negX.getY());
    assertEquals(-500, negBoth.getY());
    assertEquals(-1000, negY.getY());
  }
}
