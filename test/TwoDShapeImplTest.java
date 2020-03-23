import org.junit.Test;


import java.awt.Color;

import shapes.Dim;
import shapes.Pos;
import shapes.ShapesAllow;
import shapes.TwoDShape;
import shapes.TwoDShapeImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * This is the test class for the representation of a twoDShape.
 */
public class TwoDShapeImplTest {
  Pos pos1 = new Pos(10, 10);
  Dim dim1 = new Dim(100, 100);
  Color c1 = new Color(0, 255, 0);
  ShapesAllow type = ShapesAllow.Rect;
  TwoDShape testShape = new TwoDShapeImpl("test", pos1, dim1, c1, type);
  TwoDShape testShapeCompare = new TwoDShapeImpl("test", pos1, dim1, c1, type);
  TwoDShape testLimitedParms = new TwoDShapeImpl("small", ShapesAllow.Oval);
  TwoDShape testLimitedParmsCompare = new TwoDShapeImpl("small", ShapesAllow.Oval);

  @Test
  public void moveShapeValid() {
    assertEquals(testShape, testShapeCompare);
    Pos large = new Pos(1000, 1000);
    TwoDShapeImpl newTestShape = testShape.moveShape(large);
    assertNotEquals(newTestShape, testShapeCompare);
    Pos small = new Pos(50, 50);
    // Showing that I am changing the other objects dim and then matching.
    TwoDShapeImpl newCompare = testShapeCompare.moveShape(small);
    newCompare = newCompare.moveShape(large);
    assertEquals(newTestShape, newCompare);

    // Now Same thing with limited info
    assertEquals(testLimitedParms, testLimitedParmsCompare);
    newTestShape = testLimitedParms.moveShape(large);
    assertNotEquals(newTestShape, testLimitedParmsCompare);
    // Showing that I am changing the other objects dim and then matching.
    newCompare = testLimitedParmsCompare.moveShape(small);
    newCompare = newCompare.moveShape(large);
    assertEquals(newTestShape, newCompare);
  }

  @Test
  public void changeDimensionValid() {
    assertEquals(testShape, testShapeCompare);
    Dim large = new Dim(1000, 1000);
    TwoDShapeImpl newTestShape = testShape.changeDimension(large);
    assertNotEquals(newTestShape, testShapeCompare);
    Dim small = new Dim(50, 50);
    // Showing that I am changing the other objects dim and then matching.
    TwoDShapeImpl newCompare = testShapeCompare.changeDimension(small);
    newCompare = newCompare.changeDimension(large);
    assertEquals(newTestShape, newCompare);

    // Now Same thing with limited info
    assertEquals(testLimitedParms, testLimitedParmsCompare);
    newTestShape = testLimitedParms.changeDimension(large);
    assertNotEquals(newTestShape, testLimitedParmsCompare);
    // Showing that I am changing the other objects dim and then matching.
    newCompare = testLimitedParmsCompare.changeDimension(small);
    newCompare = newCompare.changeDimension(large);
    assertEquals(newTestShape, newCompare);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalDimChange() {
    assertEquals(testShape, testShapeCompare);
    TwoDShapeImpl newTestShape = testShape.changeDimension(new Dim(-1000, 200));
  }

  @Test
  public void changeColor() {
    assertEquals(testShape, testShapeCompare);
    int redOn = 255;
    int blueOn = 255;
    TwoDShapeImpl newTestShape = testShape.changeColor(redOn, 0, 0);
    assertNotEquals(newTestShape, testShapeCompare);
    // Showing that I am changing the other objects color and then matching again.
    TwoDShapeImpl newCompare = testShapeCompare.changeColor(0, 0, blueOn);
    newCompare = newCompare.changeColor(redOn, 0, 0);
    assertEquals(newTestShape, newCompare);

    // Now Same thing with limited info
    assertEquals(testLimitedParms, testLimitedParmsCompare);
    newTestShape = testLimitedParms.changeColor(redOn, 0, 0);
    assertNotEquals(newTestShape, testLimitedParmsCompare);
    // Showing that I am changing the other objects color and then matching again.
    newCompare = testLimitedParmsCompare.changeColor(0, 0, blueOn);
    newCompare = newCompare.changeColor(redOn, 0, 0);
    assertEquals(newTestShape, newCompare);
  }

  @Test(expected = IllegalArgumentException.class)
  public void illegalColorChange() {
    assertEquals(testShape, testShapeCompare);
    TwoDShapeImpl newTestShape = testShape.changeColor(-20, 0, 255);
  }

  @Test
  public void testToString() {
    assertEquals(testShape, testShapeCompare);
    System.out.println(testShape.toString());
    String expected = "10 10 100 100 0 255 0";
    assertEquals(expected, testShape.toString());
    assertEquals(expected, testShapeCompare.toString());
    testShapeCompare = testShape.moveShape(new Pos(20, 20));
    String newPosExpect = "20 20 100 100 0 255 0";
    assertEquals(newPosExpect, testShapeCompare.toString());
    testShapeCompare = testShape.changeDimension(new Dim(500, 500));
    String newDimExpect = "10 10 500 500 0 255 0";
    assertEquals(newDimExpect, testShapeCompare.toString());
    testShapeCompare = testShape.changeColor(0, 0, 255);
    String newColorExpect = "10 10 100 100 0 0 255";
    assertEquals(newColorExpect, testShapeCompare.toString());
  }

  // Our invariance is that a non defined shape will never be printed since its not stored in frame.
  @Test(expected = IllegalArgumentException.class)
  public void IllegalPrintOnIncompleteShape() {
    assertEquals(testLimitedParms, testLimitedParmsCompare);
    testLimitedParms.toString();
  }

  // Our invariance garuntees a name for all made shapes thus no invalid testing
  @Test
  public void getName() {
    assertEquals("test", testShape.getName());
    assertEquals("small", testLimitedParms.getName());
    assertEquals("small", testLimitedParmsCompare.getName());
  }

  // Our invariance garuntees a shapetype for all made shapes thus no invalid testing
  @Test
  public void getShapeType() {
    assertEquals(ShapesAllow.Rect, testShape.getShapeType());
    assertEquals(ShapesAllow.Oval, testLimitedParms.getShapeType());
    assertEquals(ShapesAllow.Oval, testLimitedParmsCompare.getShapeType());
  }

  @Test(expected = IllegalArgumentException.class)
  public void IllegalShapeName() {
    TwoDShapeImpl emptyStringName = new TwoDShapeImpl("", pos1, dim1, c1, type);
  }
}
