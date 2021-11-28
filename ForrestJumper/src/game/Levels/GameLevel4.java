package game.Levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.Character.CharacterController;
import game.Coins.Pickup;
import game.Reaper.ReaperCollision;
import org.jbox2d.common.Vec2;

public class GameLevel4 extends GameLevel {

    public GameLevel4() {
        super();

        // make platforms
        Shape smallPlatform = new BoxShape(2, 0.5f);

        Body platform1 = new StaticBody(this, smallPlatform);
        platform1.setPosition(new Vec2(-25, -13.5f));
        platform1.setFillColor(GameLevel.getColor());
        platform1.setLineColor(GameLevel.getColor());

        Body platform2 = new StaticBody(this, smallPlatform);
        platform2.setPosition(new Vec2(-22, -10));
        platform2.setFillColor(GameLevel.getColor());
        platform2.setLineColor(GameLevel.getColor());

        Body platform3 = new StaticBody(this, smallPlatform);
        platform3.setPosition(new Vec2(-19, -6.5f));
        platform3.setFillColor(GameLevel.getColor());
        platform3.setLineColor(GameLevel.getColor());

        Body platform4 = new StaticBody(this, smallPlatform);
        platform4.setPosition(new Vec2(-16, -3));
        platform4.setFillColor(GameLevel.getColor());
        platform4.setLineColor(GameLevel.getColor());

        Body platform5 = new StaticBody(this, smallPlatform);
        platform5.setPosition(new Vec2(-13, 0.5f));
        platform5.setFillColor(GameLevel.getColor());
        platform5.setLineColor(GameLevel.getColor());

        //make walls
        Shape smallWall = new BoxShape(0.5f, 0.5f);

        Body wall1 = new StaticBody(this, smallWall);
        wall1.setPosition(new Vec2(-20, -17));
        wall1.setAngleDegrees(90);
        wall1.setFillColor(GameLevel.getColor());
        wall1.setLineColor(GameLevel.getColor());
        wall1.addImage(new BodyImage("data/blank.png", 0.1f));

        // make circles
        Shape circle = new CircleShape(2, new Vec2(0, 0));

        Body circle1 = new StaticBody(this, circle);
        circle1.setPosition(new Vec2(-5, 1));
        circle1.setFillColor(GameLevel.getColor());
        circle1.setLineColor(GameLevel.getColor());

        Body circle2 = new StaticBody(this, circle);
        circle2.setPosition(new Vec2(3, 1));
        circle2.setFillColor(GameLevel.getColor());
        circle2.setLineColor(GameLevel.getColor());

        Body circle3 = new StaticBody(this, circle);
        circle3.setPosition(new Vec2(11, 1));
        circle3.setFillColor(GameLevel.getColor());
        circle3.setLineColor(GameLevel.getColor());

        Body circle4 = new StaticBody(this, circle);
        circle4.setPosition(new Vec2(19, 1));
        circle4.setFillColor(GameLevel.getColor());
        circle4.setLineColor(GameLevel.getColor());

        Body circle5 = new StaticBody(this, circle);
        circle5.setPosition(new Vec2(27, 1));
        circle5.setFillColor(GameLevel.getColor());
        circle5.setLineColor(GameLevel.getColor());

        // make a character
        GameLevel.getMan().setPosition(new Vec2(-25, -15.5f));
        GameLevel.getMan().addImage(CharacterController.getManC());

        // make villains
        GameLevel.getReaper1().setPosition(new Vec2(-10, -15.5f));
        GameLevel.getReaper1().startWalking(ReaperCollision.getWalkingSpeed1());

        GameLevel.getReaper2().setPosition(new Vec2(0, -15.5f));
        GameLevel.getReaper2().startWalking(ReaperCollision.getWalkingSpeed2());

        GameLevel.getReaper3().setPosition(new Vec2(10, -15.5f));
        GameLevel.getReaper3().startWalking(ReaperCollision.getWalkingSpeed3());

        // make coins
        GameLevel.getCoin1().setPosition(new Vec2(-5, 4));
        GameLevel.getCoin2().setPosition(new Vec2(3, 4));
        GameLevel.getCoin3().setPosition(new Vec2(11, 4));
        GameLevel.getCoin4().setPosition(new Vec2(19, 4));
        GameLevel.getCoin5().setPosition(new Vec2(27, 4));

        //remove unwanted bodies
        GameLevel.getHeart1().destroy();
    }

    @Override
    public boolean isComplete() {
        if (Pickup.getPoints() == 5)
            return true;
        else
            return false;
    }

    @Override
    public String getLevelName() {
        return "Level4";
    }
}