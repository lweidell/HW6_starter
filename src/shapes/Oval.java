package shapes;

import java.awt.Color;

/**
 * This class is a representation of a Oval. The dinmensions of an oval are radiusus.
 */
public class Oval extends TwoDShapeImpl {
  /**
   * This is the constructor for the initialization of a shape.
   *
   * @param name       The unique name for the object shape created.
   * @param shapesType The shape type from {@link ShapesAllow}.
   */
  public Oval(String name, ShapesAllow shapesType) {
    super(name, shapesType);
    verifyShape(shapesType);
  }

  /**
   * This constructor make s a shape given every decription provided.
   *
   * @param name      This is the unique name of the shape for the animation.
   * @param x         This will become the x position of the shape.
   * @param y         This will become the y position of the shape.
   * @param width     This will become the width of the shape.
   * @param height    This will become the height of the shape.
   * @param red       This will be the red component of the color.
   * @param green     This will be the green component of the color.
   * @param blue      This will be the blue component of the color.
   * @param shapeType This will be an enum to determine this shape type is allowed.
   */
  public Oval(
          String name,
          int x,
          int y,
          int width,
          int height,
          int red,
          int green,
          int blue,
          ShapesAllow shapeType) {
    super(name, x, y, width, height, red, green, blue, shapeType);
    verifyShape(shapeType);
  }

  /**
   * This constructor generates a new shape with the same properities of give. This will
   * be important for making an object with on change and then just feeding it into this
   * constructor.
   *
   * @param s This is the shape we want to copy.
   */
  public Oval(TwoDShapeImpl s) {
    super(s);
    verifyShape(s.shapeType);
  }

  /**
   * This is a constructor to make a shape given all the details at once.
   *
   * @param name      The unique name for the object shape created
   * @param pos       A Pos(x,y) for the location cordinates.
   * @param dim       A Dim(width,height) for the dimensions of the shape
   * @param color     The color of the shape, using Java's color class
   * @param shapeType The enum for if the shape type is allowed.
   */
  public Oval(String name, Pos pos, Dim dim, Color color, ShapesAllow shapeType) {
    super(name, pos, dim, color, shapeType);
  }

  /**
   * This is a checker for the constructor to make sure this is a rectangle.
   *
   * @param type This is the shapeType enum
   * @throws IllegalArgumentException Throws when not a Oval.
   */
  void verifyShape(ShapesAllow type) {
    if (!type.equals(ShapesAllow.Oval)) {
      throw new IllegalArgumentException("The shape being made is not Oval.");
    }
  }
}
