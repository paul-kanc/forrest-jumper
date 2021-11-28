package game.Levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.Bomb.BombItem;
import game.Character.CharacterController;
import game.Coins.Pickup;
import game.Reaper.*;
import org.jbox2d.common.Vec2;

public class GameLevel6 extends GameLevel {
    private static GhostlyReaperSensor ghostlyReaperSensor;
    private static BombItem bomb;

    public static GhostlyReaperSensor getGhostlyReaperSensor() {
        return ghostlyReaperSensor;
    }

    public static BombItem getBomb() {
        return bomb;
    }

    public GameLevel6() {
        super();

        // make platforms
        Shape sidePlatform = new BoxShape(1, 0.5f);

        Body platform1 = new StaticBody(this, sidePlatform);
        platform1.setPosition(new Vec2(-18.5f, -7));
        platform1.setFillColor(GameLevel.getColor());
        platform1.setLineColor(GameLevel.getColor());

        Body platform2 = new StaticBody(this, sidePlatform);
        platform2.setPosition(new Vec2(-18.5f, 3));
        platform2.setFillColor(GameLevel.getColor());
        platform2.setLineColor(GameLevel.getColor());

        Body platform3 = new StaticBody(this, sidePlatform);
        platform3.setPosition(new Vec2(18.5f, -7));
        platform3.setFillColor(GameLevel.getColor());
        platform3.setLineColor(GameLevel.getColor());

        Body platform4 = new StaticBody(this, sidePlatform);
        platform4.setPosition(new Vec2(18.5f, 3));
        platform4.setFillColor(GameLevel.getColor());
        platform4.setLineColor(GameLevel.getColor());

        Body platform5 = new StaticBody(this, sidePlatform);
        platform5.setPosition(new Vec2(-25, 20.5f));
        platform5.setFillColor(GameLevel.getColor());
        platform5.setLineColor(GameLevel.getColor());

        Shape midPlatform = new BoxShape(2.5f, 0.5f);

        Body platform6 = new StaticBody(this, midPlatform);
        platform6.setPosition(new Vec2(-10, -12));
        platform6.setFillColor(GameLevel.getColor());
        platform6.setLineColor(GameLevel.getColor());

        Body platform7 = new StaticBody(this, midPlatform);
        platform7.setPosition(new Vec2(-10, -2));
        platform7.setFillColor(GameLevel.getColor());
        platform7.setLineColor(GameLevel.getColor());

        Body platform8 = new StaticBody(this, midPlatform);
        platform8.setPosition(new Vec2(-10, 8));
        platform8.setFillColor(GameLevel.getColor());
        platform8.setLineColor(GameLevel.getColor());

        Body platform9 = new StaticBody(this, midPlatform);
        platform9.setPosition(new Vec2(0, -7));
        platform9.setFillColor(GameLevel.getColor());
        platform9.setLineColor(GameLevel.getColor());

        Body platform10 = new StaticBody(this, midPlatform);
        platform10.setPosition(new Vec2(0, 3));
        platform10.setFillColor(GameLevel.getColor());
        platform10.setLineColor(GameLevel.getColor());

        Body platform11 = new StaticBody(this, midPlatform);
        platform11.setPosition(new Vec2(10, -12));
        platform11.setFillColor(GameLevel.getColor());
        platform11.setLineColor(GameLevel.getColor());

        Body platform12 = new StaticBody(this, midPlatform);
        platform12.setPosition(new Vec2(10, -2));
        platform12.setFillColor(GameLevel.getColor());
        platform12.setLineColor(GameLevel.getColor());

        Body platform13 = new StaticBody(this, midPlatform);
        platform13.setPosition(new Vec2(10, 8));
        platform13.setFillColor(GameLevel.getColor());
        platform13.setLineColor(GameLevel.getColor());

        // make walls
        Shape upperWall = new BoxShape(15.5f, 0.5f);

        Body wall1 = new StaticBody(this, upperWall);
        wall1.setPosition(new Vec2(-20, 2.5f));
        wall1.setAngleDegrees(90);
        wall1.setFillColor(GameLevel.getColor());
        wall1.setLineColor(GameLevel.getColor());

        Body wall2 = new StaticBody(this, upperWall);
        wall2.setPosition(new Vec2(0, 2.5f));
        wall2.setAngleDegrees(90);
        wall2.setFillColor(GameLevel.getColor());
        wall2.setLineColor(GameLevel.getColor());

        Body wall3 = new StaticBody(this, upperWall);
        wall3.setPosition(new Vec2(20, 2.5f));
        wall3.setAngleDegrees(90);
        wall3.setFillColor(GameLevel.getColor());
        wall3.setLineColor(GameLevel.getColor());

        Shape lowerWall = new BoxShape(12.5f, 0.5f);

        Body wall4 = new StaticBody(this, lowerWall);
        wall4.setPosition(new Vec2(-10, -4));
        wall4.setAngleDegrees(90);
        wall4.setFillColor(GameLevel.getColor());
        wall4.setLineColor(GameLevel.getColor());

        Body wall5 = new StaticBody(this, lowerWall);
        wall5.setPosition(new Vec2(10, -4));
        wall5.setAngleDegrees(90);
        wall5.setFillColor(GameLevel.getColor());
        wall5.setLineColor(GameLevel.getColor());

        // make a character
        GameLevel.getMan().setPosition(new Vec2(-25, -15.5f));
        GameLevel.getMan().addImage(CharacterController.getManC());

        // make a ghostly reaper
        ghostlyReaperSensor = new GhostlyReaperSensor(this);
        GhostlyReaperSensor.getGhostlyReaper().addSensorListener(new GhostlyReaperCollision(GhostlyReaperSensor.getGhostlyReaper(), GameLevel.getMan()));
        ghostlyReaperSensor.setPosition(new Vec2(0, 0));
        ghostlyReaperSensor.setGravityScale(0);

        // make coins
        GameLevel.getCoin1().setPosition(new Vec2 (-10, 9.5f));
        GameLevel.getCoin2().setPosition(new Vec2 (10, 9.5f));
        GameLevel.getCoin3().setPosition(new Vec2 (0, 30));

        // make bomb
        bomb = new BombItem(this);
        bomb.setPosition(new Vec2(25, -15.5f));

        // remove unwanted bodies
        GameLevel.getReaper1().destroy();
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
        return "Level6";
    }
}