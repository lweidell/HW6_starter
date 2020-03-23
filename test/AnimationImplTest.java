import org.junit.Test;

import animation.Animation;
import animation.AnimationImpl;
import shapes.ShapesAllow;

import static org.junit.Assert.assertEquals;


/**
 * A test class for AnimationImpl.
 */
public class AnimationImplTest {

  @Test
  public void testPlayAnimation() {
    // No information about it yet.
    Animation anim = new AnimationImpl();
    assertEquals(anim, anim);
  }

  @Test
  public void testDescribeAnimationEmpty() {
    Animation anim = new AnimationImpl();
    assertEquals("", anim.describeAnimation());
  }

  @Test
  public void testDescribeAnimation1Shape1Tick() {
    Animation anim = new AnimationImpl();
    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(0, 10, "square", 0, 0,
            11, 11, 50, 50, 61,
            61, 255, 255, 255);
    assertEquals("shape square Rectangle\n"
                    + "motion square 0 0 0 50 50 255 255 255 1 1 1 51 51 255 255 255\n"
                    + "motion square 1 1 1 51 51 255 255 255 2 2 2 52 52 255 255 255\n"
                    + "motion square 2 2 2 52 52 255 255 255 3 3 3 53 53 255 255 255\n"
                    + "motion square 3 3 3 53 53 255 255 255 4 4 4 54 54 255 255 255\n"
                    + "motion square 4 4 4 54 54 255 255 255 5 5 5 55 55 255 255 255\n"
                    + "motion square 5 5 5 55 55 255 255 255 6 6 6 56 56 255 255 255\n"
                    + "motion square 6 6 6 56 56 255 255 255 7 7 7 57 57 255 255 255\n"
                    + "motion square 7 7 7 57 57 255 255 255 8 8 8 58 58 255 255 255\n"
                    + "motion square 8 8 8 58 58 255 255 255 9 9 9 59 59 255 255 255\n"
                    + "motion square 9 9 9 59 59 255 255 255 10 10 10 60 60 255 255 255\n",
            anim.describeAnimation());
  }

  @Test
  public void testExportMovie() {
    // No information about it yet.
    Animation anim = new AnimationImpl();
    assertEquals(anim, anim);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleOneDirectionNoShapeCreated() {
    Animation anim = new AnimationImpl();
    anim.handleOneDirection(0, 10, "square", 10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleOneDirectionShapeIsCreatedButNoDirectionIsGivenGetFrame() {
    Animation anim = new AnimationImpl();
    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.getFrameAt(0);
  }

  @Test
  public void testHandleOneDirectionShapeIsCreatedAndDirectionIsGivenShouldWork() {
    Animation anim = new AnimationImpl();

    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(0, 10, "square", 10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
    assertEquals(1, anim.getFrameAt(0).getShapesInFrame().size());

    anim.handleOneShapeCreationDirection("rect", ShapesAllow.Rect);
    anim.handleOneDirection(0, 10, "rect", 10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
    assertEquals(2, anim.getFrameAt(1).getShapesInFrame().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleOneDirectionShapeAndDirectionIsGivenButTickEndIsLessThanTickStart() {
    Animation anim = new AnimationImpl();

    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(12, 10, "square", 10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleOneDirectionShouldNotWorkBecauseIllegalDimension() {
    Animation anim = new AnimationImpl();

    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(1, 10, "square", 10, 10,
            20, 20, -50, 50, 60,
            60, 255, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleOneDirectionShouldNotWorkBecauseIllegalName() {
    Animation anim = new AnimationImpl();

    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(1, 10, "    ", 10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleOneDirectionShouldNotWorkBecauseIllegalColor() {
    Animation anim = new AnimationImpl();

    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(1, 10, "    ", 10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 600);
  }

  @Test
  public void testHandleOneDirectionShouldWorkWithNegativePosition() {
    Animation anim = new AnimationImpl();

    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(1, 10, "square", -10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
    assertEquals(1, anim.getFrameAt(1).getShapesInFrame().size());
  }

  @Test
  public void testFrameAtTick0IsEmptyWhenGivenTick1Through10() {
    Animation anim = new AnimationImpl();

    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(1, 10, "square", -10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
    assertEquals(0, anim.getFrameAt(0).getShapesInFrame().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFrameAtTick11DoesNotExistWhenGivenTick1Through10() {
    Animation anim = new AnimationImpl();

    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(1, 10, "square", -10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
    anim.getFrameAt(12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleOneShapeCreationDirectionShouldNotCreateFrames() {
    Animation anim = new AnimationImpl();
    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneShapeCreationDirection("rect", ShapesAllow.Rect);
    anim.handleOneShapeCreationDirection("squareIsRect", ShapesAllow.Rect);

    anim.getFrameAt(0);
  }

  @Test
  public void testHandleOneShapeCreationDirectionShouldWork3Shapes() {
    Animation anim = new AnimationImpl();
    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneShapeCreationDirection("rect", ShapesAllow.Rect);
    anim.handleOneShapeCreationDirection("squareIsRect", ShapesAllow.Rect);

    anim.handleOneDirection(0, 10, "square", -10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
    anim.handleOneDirection(0, 10, "rect", -10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
    anim.handleOneDirection(0, 10, "squareIsRect", -10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
    assertEquals(3, anim.getFrameAt(0).getShapesInFrame().size());
  }

  @Test
  public void testHandleOneShapeCreationDirectionWorksAsNeededInTermsOfTicks() {
    Animation anim = new AnimationImpl();
    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(1, 10, "square", -10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
    assertEquals(0, anim.getFrameAt(0).getShapesInFrame().size());
    assertEquals(1, anim.getFrameAt(1).getShapesInFrame().size());
    assertEquals(1, anim.getFrameAt(9).getShapesInFrame().size());
    assertEquals(1, anim.getFrameAt(10).getShapesInFrame().size());
    assertEquals(0, anim.getFrameAt(11).getShapesInFrame().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleOneShapeCreationDirectionShouldNotWorkWithNegativeStartTick() {
    Animation anim = new AnimationImpl();
    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(-1, 10, "square", -10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleOneShapeCreationDirectionShouldNotWorkWithNegativeEndTick() {
    Animation anim = new AnimationImpl();
    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(1, -10, "square", -10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleStaysTheSameShapeIsCreatedButNotUsedIsInvalid() {
    Animation anim = new AnimationImpl();
    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleStaysTheSame(1, 10, "square");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleStaysTheSameShapeIsNOTCreatedANDNotUsedIsInvalid() {
    Animation anim = new AnimationImpl();
    anim.handleStaysTheSame(1, 10, "square");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleStaysTheSameShapeIsCreatedANDUsedButIsInvalidTickGiven() {
    Animation anim = new AnimationImpl();
    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(2, 10, "square", -10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
    anim.handleStaysTheSame(4, 12, "square");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHandleStaysTheSameShapeIsCreatedANDUsedButIsInvalidTickGiven2() {
    Animation anim = new AnimationImpl();
    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(2, 10, "square", -10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);
    anim.handleStaysTheSame(20, 21, "square");
  }

  @Test
  public void testHandleStaysTheSameShapeIsCreatedANDUsedIsValidShouldWork() {
    Animation anim = new AnimationImpl();
    anim.handleOneShapeCreationDirection("square", ShapesAllow.Rect);
    anim.handleOneDirection(1, 3, "square", 10, 10,
            20, 20, 50, 50, 60,
            60, 255, 255, 255);

    anim.handleStaysTheSame(3, 5, "square");
    assertEquals(1, anim.getFrameAt(4).getShapesInFrame().size());
  }
}