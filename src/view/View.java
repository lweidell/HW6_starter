package view;

/**
 * A general interface for the View.
 * Has methods to set speed (how many ticks should pass per second; default is 1),
 * as well as a method to produce a specific animation of a specific type.
 */
public interface View {

  /**
   * A public method to set the animation speed (given how many ticks should pass per real second).
   * Default is one.
   * @param ticksPerSecond an int for desired number of ticks per real second.
   */
  void setSpeed(int ticksPerSecond);

  /**
   * A method to produce a view. E.g. a {@link TextualView} would produce a string,
   * a {@link VisualView} would produce a visual view, etc.
   */
  void produceView();
}
