package game.Levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.Character.CharacterController;
import game.Coins.Pickup;
import game.Reaper.ReaperCollision;
import org.jbox2d.common.Vec2;

public class GameLevel2 extends GameLevel {

    public GameLevel2() {
        super();

        // make platforms
        Shape mainPlatform = new BoxShape(12, 0.5f);

        Body platform1 = new StaticBody(this, mainPlatform);
        platform1.setPosition(new Vec2(0, -10));
        platform1.setFillColor(GameLevel.getColor());
        platform1.setLineColor(GameLevel.getColor());

        Shape smallPlatform = new BoxShape(2, 0.5f);

        Body platform2 = new StaticBody(this, smallPlatform);
        platform2.setPosition(new Vec2(-15, -13.5f));
        platform2.setFillColor(GameLevel.getColor());
        platform2.setLineColor(GameLevel.getColor());

        Body platform3 = new StaticBody(this, smallPlatform);
        platform3.setPosition(new Vec2(15, -13.5f));
        platform3.setFillColor(GameLevel.getColor());
        platform3.setLineColor(GameLevel.getColor());

        Body platform4 = new StaticBody(this, smallPlatform);
        platform4.setPosition(new Vec2(0, -5));
        platform4.setFillColor(GameLevel.getColor());
        platform4.setLineColor(GameLevel.getColor());

        // make walls

        Shape smallWall = new BoxShape(0.5f, 0.5f);

        Body wall1 = new StaticBody(this, smallWall);
        wall1.setPosition(new Vec2(-11.5f, -9));
        wall1.setAngleDegrees(90);
        wall1.setFillColor(GameLevel.getColor());
        wall1.setLineColor(GameLevel.getColor());

        Body wall2 = new StaticBody(this, smallWall);
        wall2.setPosition(new Vec2(11.5f, -9));
        wall2.setAngleDegrees(90);
        wall2.setFillColor(GameLevel.getColor());
        wall2.setLineColor(GameLevel.getColor());

        // make a character
        GameLevel.getMan().setPosition(new Vec2(-25, -15.5f));
        GameLevel.getMan().addImage(CharacterController.getManC());

        // make a villain
        GameLevel.getReaper1().setPosition(new Vec2(0, -9));
        GameLevel.getReaper1().startWalking(ReaperCollision.getWalkingSpeed1());

        // make coins
        GameLevel.getCoin1().setPosition(new Vec2(-15, -12));
        GameLevel.getCoin2().setPosition(new Vec2(15, -12));
        GameLevel.getCoin3().setPosition(new Vec2(0, -3.5f));

        // remove unwanted bodies
        GameLevel.getReaper2().destroy();
        GameLevel.getReaper3().destroy();
        GameLevel.getCoin4().destroy();
        GameLevel.getCoin5().destroy();
        GameLevel.getHeart1().destroy();
    }

    @Override
    public boolean isComplete() {
        if (Pickup.getPoints() == 3)
            return true;
        else
            return false;
    }

    @Override
    public String getLevelName() {
        return "Level2";
    }
}
