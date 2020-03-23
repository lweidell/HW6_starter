package shapes;

/**
 * An interface for a shape object. Implemented by TwoDShapeImpl.
 * Has method to get various fields and move shapes.
 */
public interface TwoDShape {

  /**
   * Getter for the name of the shape.
   *
   * @return The string name field.
   */
  String getName();

  /**
   * Getter for the ShapeType of the shape.
   *
   * @return The ShapesAllow shapeType field.
   */
  ShapesAllow getShapeType();

  /**
   * This method changes the color given and int for r,g,b, returning a new shape.
   *
   * @param red   The int red componnent of color.
   * @param green The int green component of color.
   * @param blue  The int blue component of color.
   * @return This is the new shape with changed color.
   */
  // Honestly we could just make this take a Color and when we get input we make color(red, green,
  // blue)
  TwoDShapeImpl changeColor(int red, int green, int blue);

  /**
   * This method makes a new shape with a changed dimension.
   *
   * @param d This is the given dimension to change to.
   * @return This is the final shape with changed dim.
   */
  TwoDShapeImpl changeDimension(Dim d);

  /**
   * This function handles making a new shape with a position change.
   *
   * @param p A Pos(x,y) to assign to shape.
   * @return The final new shape that was created.
   */
  // This position needs to be generated from when the controller gets in two integers
  TwoDShapeImpl moveShape(Pos p);
}
