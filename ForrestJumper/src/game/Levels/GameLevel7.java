package game.Levels;

import city.cs.engine.*;
import game.Character.CharacterController;
import game.Coins.Pickup;
import game.Reaper.ReaperCollision;
import org.jbox2d.common.Vec2;

public class GameLevel7 extends GameLevel {
    public GameLevel7() {
        super();

        // make platforms
        Shape platformShape = new BoxShape(15, 0.5f);

        Body platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(15, 7));
        platform1.setFillColor(GameLevel.getColor());
        platform1.setLineColor(GameLevel.getColor());

        Body platform2 = new StaticBody(this, platformShape);
        platform2.setPosition(new Vec2(-15, -3));
        platform2.setFillColor(GameLevel.getColor());
        platform2.setLineColor(GameLevel.getColor());

        Shape smallPlatform = new BoxShape(1, 0.5f);

        Body platform3 = new StaticBody(this, smallPlatform);
        platform3.setPosition(new Vec2(29, -12));
        platform3.setFillColor(GameLevel.getColor());
        platform3.setLineColor(GameLevel.getColor());

        Body platform4 = new StaticBody(this, smallPlatform);
        platform4.setPosition(new Vec2(29, -1));
        platform4.setFillColor(GameLevel.getColor());
        platform4.setLineColor(GameLevel.getColor());

        Body platform5 = new StaticBody(this, smallPlatform);
        platform5.setPosition(new Vec2(-29, 10));
        platform5.setFillColor(GameLevel.getColor());
        platform5.setLineColor(GameLevel.getColor());

        Body platform6 = new StaticBody(this, smallPlatform);
        platform6.setPosition(new Vec2(-29, 10));
        platform6.setFillColor(GameLevel.getColor());
        platform6.setLineColor(GameLevel.getColor());

        Shape parkourPlatform = new BoxShape(0.5f, 0.5f);

        // spawns squares with random degrees of rotation
        for (float i = 11.5f; i < 25; i = i + 6) {
            float random = (float) (Math.random() * 179 + 1);
            Body parkour1 = new StaticBody(this, parkourPlatform);
            parkour1.setPosition(new Vec2(i, -4));
            parkour1.rotateDegrees(random);
            parkour1.setFillColor(GameLevel.getColor());
            parkour1.setLineColor(GameLevel.getColor());
        }

        Body parkour2 = new StaticBody(this, parkourPlatform);
        parkour2.setPosition(new Vec2(5.5f, -4));
        parkour2.setFillColor(GameLevel.getColor());
        parkour2.setLineColor(GameLevel.getColor());

        // make walls
        Shape smallWall = new BoxShape(0.5f, 0.5f);

        Body wall1 = new StaticBody(this, smallWall);
        wall1.setPosition(new Vec2(-0.5f, -3));
        wall1.rotateDegrees(90);
        wall1.setFillColor(GameLevel.getColor());
        wall1.setLineColor(GameLevel.getColor());

        Body wall2 = new StaticBody(this, smallWall);
        wall2.setPosition(new Vec2(0.5f, 7));
        wall2.rotateDegrees(90);
        wall2.setFillColor(GameLevel.getColor());
        wall2.setLineColor(GameLevel.getColor());

        Body wall3 = new StaticBody(this, smallWall);
        wall3.setPosition(new Vec2(25, 7));
        wall3.rotateDegrees(90);
        wall3.setFillColor(GameLevel.getColor());
        wall3.setLineColor(GameLevel.getColor());

        Shape parkourWall = new BoxShape(0.5f, 0.5f);

        Body parkour1 = new StaticBody(this, parkourWall);
        parkour1.setPosition(new Vec2(5.5f, -17));
        parkour1.rotateDegrees(90);
        parkour1.setFillColor(GameLevel.getColor());
        parkour1.setLineColor(GameLevel.getColor());
        parkour1.addImage(new BodyImage("data/blank.png", 0.1f));

        // make a character
        GameLevel.getMan().setPosition(new Vec2(27, 10));
        GameLevel.getMan().addImage(CharacterController.getManC());

        // make villains
        GameLevel.getReaper1().setPosition(new Vec2(15, -15.5f));
        GameLevel.getReaper1().startWalking(ReaperCollision.getWalkingSpeed1());

        GameLevel.getReaper2().setPosition(new Vec2(-10, -1.5f));
        GameLevel.getReaper2().startWalking(ReaperCollision.getWalkingSpeed2());

        GameLevel.getReaper3().setPosition(new Vec2(10, 9));
        GameLevel.getReaper3().startWalking(ReaperCollision.getWalkingSpeed3());

        // remove unwanted bodies
        GameLevel.getCoin1().destroy();
        GameLevel.getCoin2().destroy();
        GameLevel.getCoin3().destroy();
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
        return "Level7";
    }
}
