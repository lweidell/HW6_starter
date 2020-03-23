import org.junit.Test;

import animation.Frame;
import shapes.ShapesAllow;
import shapes.TwoDShape;
import shapes.TwoDShapeImpl;

import static org.junit.Assert.assertEquals;


/**
 * This is the test class for the frame representation.
 */
public class FrameTest {

  @Test
  public void addShapeToFrameValid() {
    Frame frame = new Frame(0);
    TwoDShape square = new TwoDShapeImpl("square", ShapesAllow.Rect);

    assertEquals(0, frame.getShapesInFrame().size());
    frame.addShapeToFrame(square);
    assertEquals(1, frame.getShapesInFrame().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addShapeToFrameInvalid() {
    Frame frame = new Frame(0);
    TwoDShape square = null;

    frame.addShapeToFrame(null);
  }

  @Test
  public void frameToStringValid() {
    Frame frame = new Frame(0);
    TwoDShape square = new TwoDShapeImpl("square", 10, 10, 20, 20,
            255, 255, 255, ShapesAllow.Rect);
    TwoDShape rectangle = new TwoDShapeImpl("rectangle", 15, 15, 40, 20,
            255, 255, 255, ShapesAllow.Rect);
    frame.addShapeToFrame(square);
    frame.addShapeToFrame(rectangle);

    assertEquals("square 0 10 10 20 20 255 255 255 Rectangle\n"
            + "rectangle 0 15 15 40 20 255 255 255 Rectangle\n", frame.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void frameToStringInvalid() {
    Frame frame = new Frame(0);
    TwoDShape square = new TwoDShapeImpl("square", ShapesAllow.Rect);
    frame.addShapeToFrame(square);
    frame.toString();
  }

  @Test
  public void frameToStringEmptyFrames() {
    Frame frame = new Frame(0);
    assertEquals("", frame.toString());
  }

  @Test
  public void getTickValid() {
    Frame frame = new Frame(23);
    assertEquals(23, frame.getTick());
  }

  @Test(expected = IllegalArgumentException.class)
  public void getTickInvalid() {
    Frame frame = new Frame(-12);
  }

  @Test
  public void tickZeroShouldWork() {
    Frame frame = new Frame(0);
    assertEquals(0, frame.getShapesInFrame().size());
  }

  @Test
  public void getShapesInFrameEmpty() {
    Frame frame = new Frame(0);

    assertEquals(0, frame.getShapesInFrame().size());
  }

  @Test
  public void getShapesInFrame1Element() {
    Frame frame = new Frame(0);
    TwoDShape square = new TwoDShapeImpl("square", ShapesAllow.Rect);
    frame.addShapeToFrame(square);

    assertEquals(1, frame.getShapesInFrame().size());
  }

  @Test
  public void getShapesInFrame2Elements() {
    Frame frame = new Frame(0);
    TwoDShape square = new TwoDShapeImpl("square", 10, 10, 20, 20,
            255, 255, 255, ShapesAllow.Rect);
    TwoDShape rectangle = new TwoDShapeImpl("rectangle", 15, 15, 40, 20,
            255, 255, 255, ShapesAllow.Rect);
    frame.addShapeToFrame(square);
    frame.addShapeToFrame(rectangle);

    assertEquals(2, frame.getShapesInFrame().size());
    assertEquals(square, frame.getShapesInFrame().get(0));
  }
}