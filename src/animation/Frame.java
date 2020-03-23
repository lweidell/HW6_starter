package animation;

import shapes.TwoDShape;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of a single frame of animation at a tick value.
 */
public class Frame {
  private final List<TwoDShape> shapesInFrame = new ArrayList<>();
  private int tickOfFrame;

  /**
   * A constructor for a frame given a tick value.
   *
   * @param tickOfFrame the value tick for the frame.
   */
  public Frame(int tickOfFrame) {
    if (tickOfFrame < 0) {
      throw new IllegalArgumentException("Tick cannot be less than zero.");
    }
    this.tickOfFrame = tickOfFrame;
  }

  /**
   * This method will add a non null shape to the frame.
   *
   * @param s A two2 shape being added to list.
   */
  public void addShapeToFrame(TwoDShape s) {
    if (s == null) {
      throw new IllegalArgumentException("Added null shape to Frame.");
    }
    shapesInFrame.add(s);
  }

  /**
   * A method to return the string decription for the this ticks frame.
   *
   * @return A string animation description for the frame.
   */
  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();
    for (TwoDShape shape : shapesInFrame) {
      output.append(shape.getName()).append(" ").append(getTick()).append(" ")
              .append(shape.toString()).append(" ").append(shape.getShapeType().toString())
              .append("\n");
    }
    return output.toString();
  }

  /**
   * Provides the tick value for the current frame.
   *
   * @return The tick field for this frame.
   */
  public int getTick() {
    return this.tickOfFrame;
  }

  /**
   * A getter for all the shapes in the current frame.
   *
   * @return The list of shapes.
   */
  public List<TwoDShape> getShapesInFrame() {
    return this.shapesInFrame;
  }

  /**
   * This is a helper for checking if a shape is in a given frame so the model doesnt need to
   * handle. This helper is used in assistance for detecting illegal move.
   *
   * @param shapeInterest Shape given by model to check for illegal move.
   * @return If the shape is in the frame or not.
   */
  protected boolean isShapeInFrame(TwoDShape shapeInterest) {
    for (TwoDShape shape : shapesInFrame) {
      if (shape.getName().equals(shapeInterest.getName())) {
        return true;
      }
    }
    return false;
  }
}
