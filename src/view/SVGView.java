package view;

import animation.Animation;

/**
 * A class that implements View. This one produces an SVG animation.
 * Has one field: Animation model to store a model instance.
 */
public class SVGView implements View {
  Animation model;

  /**
   * A public constructor. Takes in an AnimationImpl instance to store it.
   * @param model an AnimationImpl instance.
   */
  public SVGView(Animation model) {
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
