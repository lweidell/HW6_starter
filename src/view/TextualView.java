package view;

import animation.Animation;

/**
 * A class that implements View interface. This one is for textual view output.
 * Has one field: Animation model to store a model instance.
 */
public class TextualView implements View {
  Animation model;

  /**
   * A constructor for the TextualView. Takes in a model instance, then just uses the model's
   * already created method to produce a textual view.
   * @param model an AnimationImpl instance.
   */
  public TextualView(Animation model) {
    this.model = model;
  }

  @Override
  public void setSpeed(int ticksPerSecond) {
    model.setSpeed(ticksPerSecond);
    //
    // we should figure out how to implement setSpeed from AnimationImpl
    //
  }

  @Override
  public void produceView() {
    model.describeAnimation();
  }
}
