package view;

import animation.Animation;

/**
 * One of the implementations of View. This one produces a visual Animation using Java Swing.
 * Has one field: Animation model to store a model instance.
 */
public class VisualView implements View {
  Animation model;

  /**
   * A public constructor for VisualView that takes in a model of type Animation.
   * @param model an instance of AnimationImpl.
   */
  public VisualView(Animation model) {
    this.model = model;
  }

  @Override
  public void setSpeed(int ticksPerSecond) {
    model.setSpeed(ticksPerSecond);
  }

  @Override
  public void produceView() {
    // ?????
  }
}
