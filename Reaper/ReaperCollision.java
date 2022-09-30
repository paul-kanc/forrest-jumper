package game.Reaper;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Levels.GameLevel;

public class ReaperCollision implements CollisionListener {
    private static int walkingSpeed1 = 9;
    private static int walkingSpeed2 = -11;
    private static int walkingSpeed3 = 7;

    public static int getWalkingSpeed1() {
        return walkingSpeed1;
    }

    public static int getWalkingSpeed2() {
        return walkingSpeed2;
    }

    public static int getWalkingSpeed3() {
        return walkingSpeed3;
    }

    public static void setWalkingSpeed1(int walkingSpeed1) {
        ReaperCollision.walkingSpeed1 = walkingSpeed1;
    }

    public static void setWalkingSpeed2(int walkingSpeed2) {
        ReaperCollision.walkingSpeed2 = walkingSpeed2;
    }

    public static void setWalkingSpeed3(int walkingSpeed3) {
        ReaperCollision.walkingSpeed3 = walkingSpeed3;
    }

    @Override
    public void collide(CollisionEvent reverse) {
        if (reverse.getReportingBody() == GameLevel.getReaper1() && ((reverse.getOtherBody().getAngleDegrees() < 100 &&
                reverse.getOtherBody().getAngleDegrees() > 80) || (reverse.getOtherBody() == GameLevel.getReaper2() ||
                reverse.getOtherBody() == GameLevel.getReaper3()))) {
            walkingSpeed1 = -walkingSpeed1;
            GameLevel.getReaper1().startWalking(walkingSpeed1);
        }

        if (reverse.getReportingBody() == GameLevel.getReaper2() && ((reverse.getOtherBody().getAngleDegrees() < 100 &&
                reverse.getOtherBody().getAngleDegrees() > 80) || (reverse.getOtherBody() == GameLevel.getReaper1() ||
                reverse.getOtherBody() == GameLevel.getReaper3()))) {
            walkingSpeed2 = -walkingSpeed2;
            GameLevel.getReaper2().startWalking(walkingSpeed2);
        }

        if (reverse.getReportingBody() == GameLevel.getReaper3() && ((reverse.getOtherBody().getAngleDegrees() < 100 &&
                reverse.getOtherBody().getAngleDegrees() > 80) || (reverse.getOtherBody() == GameLevel.getReaper1() ||
                reverse.getOtherBody() == GameLevel.getReaper2()))) {
            walkingSpeed3 = -walkingSpeed3;
            GameLevel.getReaper3().startWalking(walkingSpeed3);
        }
    }
}