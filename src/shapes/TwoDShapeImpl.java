package shapes;

import java.awt.Color;
import java.util.Objects;

/**
 * This is a representation for any type of shapeAllowed in our animator.
 */
public class TwoDShapeImpl implements TwoDShape {
  private String name;
  private Pos pos;
  protected Dim dim;
  private Color color;
  protected ShapesAllow shapeType;

  /**
   * This is the constructor for the initialization of a shape.
   *
   * @param name       The unique name for the object shape created.
   * @param shapesType The shape type from {@link ShapesAllow}.
   */
  public TwoDShapeImpl(String name, ShapesAllow shapesType) {
    verifyName(name);
    this.name = name;
    this.shapeType = shapesType;
    this.pos = null;
    this.dim = null;
    this.color = null;
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
  public TwoDShapeImpl(
          String name,
          int x,
          int y,
          int width,
          int height,
          int red,
          int green,
          int blue,
          ShapesAllow shapeType) {
    verifyName(name);
    this.name = name;
    this.pos = new Pos(x, y);
    this.dim = new Dim(width, height);
    this.color = new Color(red, green, blue);
    this.shapeType = shapeType;
  }

  /**
   * This constructor generates a new shape with the same properities of give. This will
   * be important for making an object with on change and then just feeding it into this
   * constructor.
   *
   * @param s This is the shape we want to copy.
   */
  public TwoDShapeImpl(TwoDShapeImpl s) {
    this.name = s.name;
    this.pos = s.pos;
    this.dim = s.dim;
    this.color = s.color;
    this.shapeType = s.shapeType;
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
  public TwoDShapeImpl(String name, Pos pos, Dim dim, Color color, ShapesAllow shapeType) {
    verifyName(name);
    this.name = name;
    this.pos = pos;
    this.dim = dim;
    this.color = color;
    this.shapeType = shapeType;
  }

  /**
   * This is a helper to make sure name is not given a empty string.
   *
   * @param name This is the passed name for shape from construcotr.
   * @throws IllegalArgumentException If the name is black that is not acceptable.
   */
  private void verifyName(String name) {
    if (name.isBlank() || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty or blank");
    }
  }

  /**
   * This function handles making a new shape with a position change.
   *
   * @param p A Pos(x,y) to assign to shape.
   * @return The final new shape that was created.
   */
  // This position needs to be generated from when the controller gets in two integers
  public TwoDShapeImpl moveShape(Pos p) {
    TwoDShapeImpl finalShape = duplicateShape();
    finalShape.pos = p;
    return finalShape;
  }

  /**
   * This method makes a new shape with a changed dimension.
   *
   * @param d This is the given dimension to change to.
   * @return This is the final shape with changed dim.
   */
  public TwoDShapeImpl changeDimension(Dim d) {
    TwoDShapeImpl finalShape = duplicateShape();
    finalShape.dim = d;
    return finalShape;
  }

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
  public TwoDShapeImpl changeColor(int red, int green, int blue) {
    Color newColor = new Color(red, green, blue);
    TwoDShapeImpl finalShape = duplicateShape();
    finalShape.color = newColor;
    return finalShape;
  }

  /**
   * This is a method to duplicate the current shape.
   *
   * @return The new cloned shape.
   */
  private TwoDShapeImpl duplicateShape() {
    String name = this.name;
    Pos pos = this.pos;
    Dim dim = this.dim;
    Color color = this.color;
    ShapesAllow shapeType = this.shapeType;

    return new TwoDShapeImpl(name, pos, dim, color, shapeType);
  }

  public String getName() {
    return this.name;
  }

  public ShapesAllow getShapeType() {
    return this.shapeType;
  }

  /**
   * This is a method to get the string details for a shape.
   *
   * @return The string details of the shape.
   */
  @Override
  public String toString() {

    if (this.pos == null || this.dim == null || this.color == null) {
      throw new IllegalArgumentException("This shape has not received a direction yet.");
    }
    return pos.getX() + " " + pos.getY() + " " + dim.getWidth() + " " + dim.getHeight() + " "
            + color.getRed() + " " + color.getGreen() + " " + color.getBlue();
  }

  /**
   * Over riding the equals in shapes for testing functions without needing public getters.
   *
   * @param obj This is another TwoDShape that will be compared against this.
   * @return If the shape field values are the same.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (!TwoDShape.class.isAssignableFrom(obj.getClass())) {
      return false;
    }

    final TwoDShapeImpl other = (TwoDShapeImpl) obj;
    return Objects.equals(this.name, other.name)
            && Objects.equals(this.pos, other.pos)
            && Objects.equals(this.dim, other.dim)
            && Objects.equals(this.color, other.color)
            && Objects.equals(this.shapeType, other.shapeType);
  }

  /**
   * Overriding hashCode() method.
   * @return a new hashCode.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.shapeType, this.color, this.dim, this.pos);
  }
}
