package animation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import shapes.Dim;
import shapes.Pos;
import shapes.ShapesAllow;
import shapes.TwoDShape;
import shapes.TwoDShapeImpl;

/**
 * This is a representation of the model for the animation generator.
 */
public class AnimationImpl implements Animation {
  private final List<Frame> frames;
  private final List<TwoDShapeImpl> createdShapes;

  /**
   * Constructor to make an empty lost for frames when given nothing.
   */
  public AnimationImpl() {
    this.frames = new ArrayList<>();
    this.createdShapes = new ArrayList<>();
  }

  /**
   * A method to make a full documentation on all frames in a string format.
   *
   * @return The string for AnimationDescription to read
   */
  private String outputStringGen() {
    StringBuilder output = new StringBuilder();
    for (Frame frame : this.frames) {
      output.append(frame.toString());
    }
    return output.toString();
  }

  public void playAnimation() {
    // This is a stub since we dont know what it means yet to play an animation, we have no view or
    // controller, or any graphical representation.
    // We do know that speed will be involved.
  }

  public void setSpeed(int ticksPerSecond) {
  }

  public String describeAnimation() {
    AnimationDescription anim = new AnimationDescription(outputStringGen());
    return anim.getDescription();
  }

  /**
   * This method will get a particular frame for the user.
   *
   * @param tick The tick for the frame of interest.
   * @return The frame at that given tick.
   */
  public Frame getFrameAt(int tick) {
    if (tick > frames.size()) {
      throw new IllegalArgumentException("This is larger than frame pool.");
    }
    for (Frame frame : frames) {
      if (frame.getTick() == tick) {
        return frame;
      }
    }
    throw new IllegalArgumentException("No frame has this tick.");
  }

  public Movie exportMovie() {
    // We are just returning a Movie We dont know what this is.
    return new Movie();
  }

  /**
   * A method for creating the given number of frames in the model.
   *
   * @param number a number of frames to exist in the model.
   */
  private void addFrames(int number) {
    if (number < 0) {
      throw new IllegalArgumentException("No negative input please");
    }

    while (frames.size() - 1 < number + 1) {
      Frame frame = new Frame(frames.size());
      frames.add(frame);
    }
  }

  /**
   * A method to handle one single user direction that is given by the controller.
   *
   * @param tickStart      int for the starting tick.
   * @param tickEnd        int for the ending tick.
   * @param name           String for the shape name.
   * @param posXStart      The int for the starting X position.
   * @param posYStart      The int for the starting Y position.
   * @param posXEnd        The int for the ending X position.
   * @param posYEnd        The int for the ending Y position.
   * @param dimWidthStart  The int for the starting width.
   * @param dimHeightStart The int for the starting height.
   * @param dimWidthEnd    The int for the ending width.
   * @param dimHeightEnd   The int for the ending height.
   * @param red            The int for the red component of color.
   * @param green          The int for the green component of color.
   * @param blue           The int for the
   * @throws IllegalArgumentException If the move is illegal illegal argument is thrown.
   */
  // In the controller, throw IAE if end tick is smaller than then the start tick.
  public void handleOneDirection(
          int tickStart,
          int tickEnd,
          String name,
          int posXStart,
          int posYStart,
          int posXEnd,
          int posYEnd,
          int dimWidthStart,
          int dimHeightStart,
          int dimWidthEnd,
          int dimHeightEnd,
          int red,
          int green,
          int blue) {

    if (tickEnd < tickStart) {
      throw new IllegalArgumentException("End tick cannot be less than start tick.");
    }
    addFrames(tickEnd);

    Pos posStart = new Pos(posXStart, posYStart);
    Pos posEnd = new Pos(posXEnd, posYEnd);
    Dim dimStart = new Dim(dimWidthStart, dimHeightStart);
    Dim dimEnd = new Dim(dimWidthEnd, dimHeightEnd);
    Color color = new Color(red, green, blue);
    ShapesAllow shapeType = createdShapes.get(retriveShapeIndex(name)).getShapeType();
    int tickInterval = tickEnd - tickStart + 1;

    for (int i = 0; i <= (tickEnd - tickStart); i++) {
      float xPosIncrementor = (incrementEachTick(tickInterval, posEnd.getX(), posStart.getX()) * i)
              + posXStart;
      float yPosIncrementor = (incrementEachTick(tickInterval, posEnd.getY(), posStart.getY()) * i)
              + posYStart;
      float widthIncrementor =
              (incrementEachTick(tickInterval, dimEnd.getWidth(), dimStart.getWidth()) * i)
                      + dimWidthStart;
      float heightIncrementor =
              (incrementEachTick(tickInterval, dimEnd.getHeight(), dimStart.getHeight()) * i)
                      + dimHeightStart;

      Dim newDim = new Dim((int) widthIncrementor, (int) heightIncrementor);
      Pos newPos = new Pos((int) xPosIncrementor, (int) yPosIncrementor);
      TwoDShapeImpl newShape = new TwoDShapeImpl(name, newPos, newDim, color, shapeType);

      // After making the shape before placing we verify it is okay.
      if (isShapeUsed(name)) {
        if (!isLegalMove(tickStart, newShape)) {
          throw new IllegalArgumentException("This move is a teleportation or override.");
        }
      }

      getFrameAt(tickStart + i).addShapeToFrame(newShape);

      if (i == (tickEnd - tickStart)) {
        int index = retriveShapeIndex(name);
        createdShapes.remove(retriveShapeIndex(name));
        createdShapes.add(index, newShape);
      }
    }
  }

  /**
   * A method to handle a case when a user wants the shape to do nothing from tick1 to tick2.
   *
   * @param tickStart int for the starting tick.
   * @param tickEnd   int for the ending tick.
   * @param name      String for shape name.
   * @throws IllegalArgumentException For teleportation, override, or the shape has not been set.
   */
  // Made it illegal to remain same over a time if you havent used the shape yet for our design
  // purposes.
  public void handleStaysTheSame(int tickStart, int tickEnd, String name) {
    if (tickEnd < tickStart) {
      throw new IllegalArgumentException("End tick cannot be less than start tick.");
    }
    addFrames(tickEnd);

    for (int i = 0; i < (tickEnd - tickStart); i++) {
      int indexOfShape = retriveShapeIndex(name);

      TwoDShape newShape = new TwoDShapeImpl(createdShapes.get(indexOfShape));

      // After making the shape before placing we verify it is okay.
      if (!isLegalMove(tickStart, newShape)) {
        throw new IllegalArgumentException("This move is a teleportation or override.");
      }
      if (!isShapeUsed(name)) {
        throw new IllegalArgumentException(
                "This shape hasnt been used, cannot remain the same. Need to set.");
      }

      getFrameAt(tickStart + i).addShapeToFrame(newShape);
    }
  }

  /**
   * A method for adding to the created shapes list if not already there.
   *
   * @param name The string name for the shape.
   * @param type The type for the shape.
   * @throws IllegalArgumentException If the shape has already been made.
   */
  public void handleOneShapeCreationDirection(String name, ShapesAllow type) {
    try {
      retriveShapeIndex(name);
      throw new IllegalArgumentException("This shape has already been made.");
    } catch (IllegalArgumentException e) {
      createdShapes.add(new TwoDShapeImpl(name, type));
    }
  }

  /**
   * A helper method to retrieve the shape index from the list of created shapes.
   *
   * @param name a String for shape name.
   * @return int for index of this shape in the list of created objects.
   * @throws IllegalArgumentException if there is no shape with this name.
   */
  private int retriveShapeIndex(String name) {
    for (TwoDShapeImpl shape : createdShapes) {
      if (shape.getName().equals(name)) {
        return createdShapes.indexOf(shape);
      }
    }
    throw new IllegalArgumentException("This object with this name is not created");
  }

  /**
   * A helper method to determine if a shape has been created but not set anywhere.
   *
   * @param name The unique name of the shape.
   * @return True if the shape is used otherwise false.
   */
  private boolean isShapeUsed(String name) {
    try {
      TwoDShape shape = createdShapes.get(retriveShapeIndex(name));
      TwoDShape emptyShape = new TwoDShapeImpl(name, shape.getShapeType());
      return !shape.equals(emptyShape);
    } catch (IllegalArgumentException e) {
      return false;
    }
  }

  /**
   * A helper method to calculate the X, Y, size, or height increment for each tick.
   *
   * @param tickInterval the interval from start tick to end tick.
   * @param endPoint     can either be end X, end Y, end size, or end height of the shape.
   * @param startPoint   can either be start X, start Y, start size, or start height of the shape.
   * @return float that represents the incrementer for each tick.
   */
  private float incrementEachTick(int tickInterval, int endPoint, int startPoint) {
    return (float) ((endPoint - startPoint) / tickInterval);
  }

  /**
   * This is a boolean helper for the mode to deligate whether teleportation or override has
   * occured.
   *
   * @param startTick This is the first tick the user gives on what the user is trying to make.
   * @param shape     This is the shape that the user is trying to put in.
   * @return True is the move is legal and false if the move is illegal.
   */
  private boolean isLegalMove(int startTick, TwoDShape shape) {
    if (startTick == 0 || getFrameAt(startTick).isShapeInFrame(shape)) {
      return !getFrameAt(startTick + 1).isShapeInFrame(shape);
    } else {
      return false;
    }
  }
}
