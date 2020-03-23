
import org.junit.Test;

import shapes.ShapesAllow;

import static org.junit.Assert.assertEquals;

/**
 * This is the test class for enums of shapes allowed.
 */
public class ShapesAllowTest {
  ShapesAllow testRect = ShapesAllow.Rect;
  ShapesAllow testOval = ShapesAllow.Oval;

  @Test
  public void testToString() {
    assertEquals("Rectangle", testRect.toString());
    assertEquals("Oval", testOval.toString());
  }
}
