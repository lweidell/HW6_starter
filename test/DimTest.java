
import org.junit.Test;

import shapes.Dim;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for dimension representations.
 */
public class DimTest {
  Dim test1 = new Dim(10, 20);
  Dim zeroWidth = new Dim(0, 1000);
  Dim zeroHeight = new Dim(10000, 0);

  @Test
  public void getWidth() {
    assertEquals(10, test1.getWidth());
    assertEquals(0, zeroWidth.getWidth());
    assertEquals(10000, zeroHeight.getWidth());
  }

  @Test
  public void getHeight() {
    assertEquals(20, test1.getHeight());
    assertEquals(1000, zeroWidth.getHeight());
    assertEquals(0, zeroHeight.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalNegativeWidth() {
    Dim negWidth = new Dim(-1000, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalNegativeBoth() {
    Dim negBoth = new Dim(-500, -500);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalNegativeHeight() {
    Dim negHeight = new Dim(0, -1000);
  }
}