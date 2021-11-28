package game.Levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.Character.CharacterController;
import game.Coins.Pickup;
import game.Reaper.ReaperCollision;
import org.jbox2d.common.Vec2;

public class GameLevel3 extends GameLevel {

    public GameLevel3() {
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

        Body platform6 = new StaticBody(this, smallPlatform);
        platform6.setPosition(new Vec2(25, -13.5f));
        platform6.setFillColor(GameLevel.getColor());
        platform6.setLineColor(GameLevel.getColor());

        Body platform7 = new StaticBody(this, smallPlatform);
        platform7.setPosition(new Vec2(22, -10));
        platform7.setFillColor(GameLevel.getColor());
        platform7.setLineColor(GameLevel.getColor());

        Body platform8 = new StaticBody(this, smallPlatform);
        platform8.setPosition(new Vec2(19, -6.5f));
        platform8.setFillColor(GameLevel.getColor());
        platform8.setLineColor(GameLevel.getColor());

        Body platform9 = new StaticBody(this, smallPlatform);
        platform9.setPosition(new Vec2(16, -3));
        platform9.setFillColor(GameLevel.getColor());
        platform9.setLineColor(GameLevel.getColor());

        Body platform10 = new StaticBody(this, smallPlatform);
        platform10.setPosition(new Vec2(13, 0.5f));
        platform10.setFillColor(GameLevel.getColor());
        platform10.setLineColor(GameLevel.getColor());

        Body platform11 = new StaticBody(this, smallPlatform);
        platform11.setPosition(new Vec2(0, -12));
        platform11.setFillColor(GameLevel.getColor());
        platform11.setLineColor(GameLevel.getColor());

        Shape mainPlatform = new BoxShape(4, 0.5f);

        Body platform12 = new StaticBody(this, mainPlatform);
        platform12.setPosition(new Vec2(-8, 4));
        platform12.setFillColor(GameLevel.getColor());
        platform12.setLineColor(GameLevel.getColor());

        Body platform13 = new StaticBody(this, mainPlatform);
        platform13.setPosition(new Vec2(8, 4));
        platform13.setFillColor(GameLevel.getColor());
        platform13.setLineColor(GameLevel.getColor());

        Shape tombRoof = new BoxShape(4, 0.5f);

        Body platform14 = new StaticBody(this, tombRoof);
        platform14.setPosition(new Vec2(9, -8));
        platform14.setFillColor(GameLevel.getColor());
        platform14.setLineColor(GameLevel.getColor());

        Body platform15 = new StaticBody(this, tombRoof);
        platform15.setPosition(new Vec2(-9, -8));
        platform15.setFillColor(GameLevel.getColor());
        platform15.setLineColor(GameLevel.getColor());

        Shape miniPlatform = new BoxShape(1, 0.5f);

        Body platform16 = new StaticBody(this, miniPlatform);
        platform16.setPosition(new Vec2(12, -12));
        platform16.setFillColor(GameLevel.getColor());
        platform16.setLineColor(GameLevel.getColor());

        // make walls
        Shape smallWall = new BoxShape(0.5f, 0.5f);

        Body wall1 = new StaticBody(this, smallWall);
        wall1.setPosition(new Vec2(-11.5f, 5));
        wall1.setAngleDegrees(90);
        wall1.setFillColor(GameLevel.getColor());
        wall1.setLineColor(GameLevel.getColor());

        Body wall2 = new StaticBody(this, smallWall);
        wall2.setPosition(new Vec2(-4.5f, 5));
        wall2.setAngleDegrees(90);
        wall2.setFillColor(GameLevel.getColor());
        wall2.setLineColor(GameLevel.getColor());

        Body wall3 = new StaticBody(this, smallWall);
        wall3.setPosition(new Vec2(11.5f, 5));
        wall3.setAngleDegrees(90);
        wall3.setFillColor(GameLevel.getColor());
        wall3.setLineColor(GameLevel.getColor());

        Body wall4 = new StaticBody(this, smallWall);
        wall4.setPosition(new Vec2(4.5f, 5));
        wall4.setAngleDegrees(90);
        wall4.setFillColor(GameLevel.getColor());
        wall4.setLineColor(GameLevel.getColor());

        Shape wall = new BoxShape(6, 0.5f);

        Body wall5 = new StaticBody(this, wall);
        wall5.setPosition(new Vec2(-4.5f, -2.5f));
        wall5.setAngleDegrees(90);
        wall5.setFillColor(GameLevel.getColor());
        wall5.setLineColor(GameLevel.getColor());

        Body wall6 = new StaticBody(this, wall);
        wall6.setPosition(new Vec2(4.5f, -2.5f));
        wall6.setAngleDegrees(90);
        wall6.setFillColor(GameLevel.getColor());
        wall6.setLineColor(GameLevel.getColor());

        Shape tombWall = new BoxShape(4.5f, 0.5f);

        Body wall7 = new StaticBody(this, tombWall);
        wall7.setPosition(new Vec2(-13.5f, -12));
        wall7.setAngleDegrees(90);
        wall7.setFillColor(GameLevel.getColor());
        wall7.setLineColor(GameLevel.getColor());

        Body wall8 = new StaticBody(this, tombWall);
        wall8.setPosition(new Vec2(13.5f, -12));
        wall8.setAngleDegrees(90);
        wall8.setFillColor(GameLevel.getColor());
        wall8.setLineColor(GameLevel.getColor());

        // make a character
        GameLevel.getMan().setPosition(new Vec2(-25, -15.5f));
        GameLevel.getMan().addImage(CharacterController.getManC());

        // make villains
        GameLevel.getReaper1().setPosition(new Vec2(0, -15.5f));
        GameLevel.getReaper1().startWalking(ReaperCollision.getWalkingSpeed1());

        GameLevel.getReaper2().setPosition(new Vec2(8, 5.5f));
        GameLevel.getReaper2().startWalking(ReaperCollision.getWalkingSpeed2());

        GameLevel.getReaper3().setPosition(new Vec2(-8, 5.5f));
        GameLevel.getReaper3().startWalking(ReaperCollision.getWalkingSpeed3());

        // make coins
        GameLevel.getCoin1().setPosition(new Vec2(-13, 2));
        GameLevel.getCoin2().setPosition(new Vec2(25, -12));
        GameLevel.getCoin3().setPosition(new Vec2(0, -10.5f));

        // make heart
        GameLevel.getHeart1().setPosition(new Vec2(12, -10.5f));

        // remove unwanted bodies
        GameLevel.getCoin4().destroy();
        GameLevel.getCoin5().destroy();
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
        return "Level3";
    }
}