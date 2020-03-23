package animation;

import shapes.ShapesAllow;

/**
 * An interface for Animation. Used by AnimationImpl.
 * Has various method to play an animation, such as playAnimation, handle directions, etc.
 */
public interface Animation {

  /**
   * A method to set the animation's speed.
   * @param ticksPerSecond an int representing how many ticks should pass per real second.
   */
  void setSpeed(int ticksPerSecond);

  /**
   * This is the method to play the entire animation visually.
   */
  void playAnimation();

  /**
   * This is the method for returning the animation description.
   */
  String describeAnimation();

  /**
   * Method to give the animation in Movie form.
   *
   * @return The Movie for the animation.
   */
  Movie exportMovie();

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
  void handleOneDirection(
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
          int blue);

  /**
   * A method to handle a case when a user wants the shape to do nothing from tick1 to tick2.
   *
   * @param tickStart int for the starting tick.
   * @param tickEnd   int for the ending tick.
   * @param name      String for shape name.
   * @throws IllegalArgumentException For teleportation, override, or the shape has not been set.
   */
  void handleStaysTheSame(int tickStart, int tickEnd, String name);

  /**
   * A method for adding to the created shapes list if not already there.
   *
   * @param name The string name for the shape.
   * @param type The type for the shape.
   * @throws IllegalArgumentException If the shape has already been made.
   */
  void handleOneShapeCreationDirection(String name, ShapesAllow type);

  /**
   * This method will get a particular frame for the user.
   *
   * @param tick The tick for the frame of interest.
   * @return The frame at that given tick.
   */
  Frame getFrameAt(int tick);
}
