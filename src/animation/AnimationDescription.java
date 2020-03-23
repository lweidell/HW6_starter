package animation;

import java.util.ArrayList;

/**
 * A class that helps make the animation description. Has a single field description (String) that
 * stores the animation description.
 */
public class AnimationDescription {
  private String description;

  /**
   * A constructor for this class. Takes a string to use in description.
   *
   * @param s a string for description.
   */
  public AnimationDescription(String s) {
    this.description = animationTextDesc(allShapesDesc(s));
  }

  /**
   * This is the getter for the animation description.
   *
   * @return The String of the description.
   */
  public String getDescription() {
    return this.description;
  }


  /**
   * This is a method to get all descriptions ordered for each shape in a list for each shape.
   *
   * @param s The string output from all frames being a string. From outputStringGen in model.
   * @return A list of all the descriptions for one shape in a list of multiple shapes strings.
   */
  private ArrayList<ArrayList<String>> allShapesDesc(String s) {
    System.out.println(s);
    String[] lines = s.split("\n");

    ArrayList<ArrayList<String>> compiledString = new ArrayList<>();
    for (String shape : lines) {
      String[] split = shape.split("\\s+");

      boolean notFound = true;
      for (ArrayList<String> oneShape : compiledString) {
        String[] splitArray = oneShape.get(0).split("\\s+");
        if (split[0].equals(splitArray[0])) {
          notFound = false;
          oneShape.add(shape);
        }
      }
      if (notFound) {
        compiledString.add(new ArrayList<>());
        compiledString.get(compiledString.size() - 1).add(shape);
      }
    }
    return compiledString;
  }

  /**
   * This takes the list of a list of strings from above and makes the animation description in the
   * proper format.
   *
   * @param compiledList This is the compiled list of list of string for the shapes but not
   *                     formatted.
   * @return The proper animation description as wanted in the assigment.
   */
  private String animationTextDesc(ArrayList<ArrayList<String>> compiledList) {
    StringBuilder description = new StringBuilder();

    //Make sure that the array is not just empty. If is return "".
    //Invariance that model will give me empty of valid shape string.
    if (compiledList.get(0).get(0).equals("")) {
      return "";
    }
    for (ArrayList<String> shapeData : compiledList) {
      description.append(oneShapeDescriptor(shapeData));
    }
    return description.toString();
  }

  /**
   * This helper converts one arraylist of a shape to its properly formated string for the
   * description.
   *
   * @param shape This is the single list for one shape.
   * @return The properly formated form for one shape.
   */
  private String oneShapeDescriptor(ArrayList<String> shape) {
    StringBuilder output = new StringBuilder();
    // Need to figure ot what to do if only one move:
    // Now need to handle if going index out of bounds
    // @TODO
    String start = shape.get(0);
    String[] startSplit = start.split("\\s+");
    String name = startSplit[0];
    String shapeType = startSplit[startSplit.length - 1];
    output.append("shape ").append(name).append(" ").append(shapeType).append("\n");
    for (int i = 0; i < shape.size(); i++) {

      String end;

      // If i is less than size and shape is not one index you are done.
      if (shape.size() != 1 && i == shape.size() - 1) {
        continue;
      }

      // If the shape only has one move;
      if (shape.size() == 1) {
        start = shape.get(i);
        end = shape.get(i);
      } else {
        start = shape.get(i);
        end = shape.get(i + 1);
      }
      startSplit = start.split("\\s+");
      String[] endSplit = end.split("\\s+");
      output.append("motion ");
      // This is length + 2 since I dont want shape type to appear
      for (int k = 0; k < startSplit.length - 1; k++) {
        output.append(startSplit[k]).append(" ");
      }
      // I dont want name(0) or type to appear(last index)
      for (int j = 1; j < endSplit.length - 1; j++) {
        if (j == endSplit.length - 2) {
          output.append(endSplit[j]).append("\n");
          continue;
        }
        output.append(endSplit[j]).append(" ");
      }
    }
    return output.toString();
  }
}
