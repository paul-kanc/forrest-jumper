/**
 * Holds general functionality for every level.
 */

package game.Levels;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.Bomb.BombItemPickup;
import game.Character.Character;
import game.Heart.Lives;
import game.Coins.Coin;
import game.Coins.Pickup;
import game.Heart.Heart;
import game.Reaper.Reaper;
import game.Reaper.ReaperCollision;
import org.jbox2d.common.Vec2;

import java.awt.*;

public abstract class GameLevel extends World {
    /**
     * The character that the player will control.
     */
    private static Character man;
    /**
     * The villains,
     */
    private static Reaper reaper1, reaper2, reaper3;
    /**
     * The coins.
     */
    private static Coin coin1, coin2, coin3, coin4, coin5;
    /**
     * The heart that will increase the player health when collected.
     */
    private static Heart heart1;
    /**
     * The frame of the world so the player can't run off screen.
     */
    private static Body ground, ceiling, leftWall, rightWall;
    /**
     * Sets the difficulty.
     */
    private static Boolean easy = false, medium = false, hard = false;
    /**
     * The base color before a difficulty is selected.
     */
    private static Color color = Color.white;

    public static Character getMan() {
        return man;
    }

    public static Reaper getReaper1() {
        return reaper1;
    }

    public static Reaper getReaper2() {
        return reaper2;
    }

    public static Reaper getReaper3() {
        return reaper3;
    }

    public static Coin getCoin1() {
        return coin1;
    }

    public static Coin getCoin2() {
        return coin2;
    }

    public static Coin getCoin3() {
        return coin3;
    }

    public static Coin getCoin4() {
        return coin4;
    }

    public static Coin getCoin5() {
        return coin5;
    }

    public static Heart getHeart1() {
        return heart1;
    }

    public static Body getGround() {
        return ground;
    }

    public static Body getCeiling() {
        return ceiling;
    }

    public static Body getLeftWall() {
        return leftWall;
    }

    public static Body getRightWall() {
        return rightWall;
    }

    public static Color getColor() {
        return color;
    }

    /**
     * Sets the difficulty to easy.
     */
    public static void easy() {
        easy = true;
        medium = false;
        hard = false;
    }

    /**
     * Sets the difficulty to medium.
     */
    public static void medium() {
        easy = false;
        medium = true;
        hard = false;
    }

    /**
     * Sets the difficulty to hard.
     */
    public static void hard() {
        easy = false;
        medium = false;
        hard = true;
    }

    public abstract boolean isComplete();

    /**
     * Everything created with this method will be constant through out every level unless it is edited
     * separately.
     */
    public GameLevel() {
        // make the ground and ceiling
        Shape groundShape = new BoxShape(30, 0.5f);
        ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0, -17f));
        ground.addImage(new BodyImage("data/blank.png", 0.1f));
        ceiling = new StaticBody(this, groundShape);
        ceiling.setPosition(new Vec2(0, 17.75f));
        ceiling.addImage(new BodyImage("data/blank.png", 0.1f));

        // make walls
        Shape wallShape = new BoxShape(18, 0.5f);
        leftWall = new StaticBody(this, wallShape);
        leftWall.setPosition(new Vec2(-30.5f, 0));
        leftWall.rotateDegrees(90);
        leftWall.addImage(new BodyImage("data/blank.png", 0.1f));
        rightWall = new StaticBody(this, wallShape);
        rightWall.setPosition(new Vec2(30.5f, 0));
        rightWall.rotateDegrees(90);
        rightWall.addImage(new BodyImage("data/blank.png", 0.1f));

        if (easy) {
            color = Color.PINK;
        }

        else if (medium) {
            color = Color.BLUE;
            ReaperCollision.setWalkingSpeed1(ReaperCollision.getWalkingSpeed1()+5);
            ReaperCollision.setWalkingSpeed2(ReaperCollision.getWalkingSpeed2()-5);
            ReaperCollision.setWalkingSpeed3(ReaperCollision.getWalkingSpeed3()+5);
        }

        else if (hard) {
            color = Color.RED;
            ReaperCollision.setWalkingSpeed1(ReaperCollision.getWalkingSpeed1()+10);
            ReaperCollision.setWalkingSpeed2(ReaperCollision.getWalkingSpeed2()-10);
            ReaperCollision.setWalkingSpeed3(ReaperCollision.getWalkingSpeed3()+10);
        }

        // make a character
        man = new Character(this);
        man.addCollisionListener(new Lives(man, null));
        man.addCollisionListener(new BombItemPickup());

        // make villains
        reaper1 = new Reaper(this);
        reaper1.addCollisionListener(new ReaperCollision());

        reaper2 = new Reaper(this);
        reaper2.addCollisionListener(new ReaperCollision());

        reaper3 = new Reaper(this);
        reaper3.addCollisionListener(new ReaperCollision());

        // make coins
        coin1 = new Coin(this);
        coin1.addCollisionListener(new Pickup(man, coin1));

        coin2 = new Coin(this);
        coin2.addCollisionListener(new Pickup(man, coin2));

        coin3 = new Coin(this);
        coin3.addCollisionListener(new Pickup(man, coin3));

        coin4 = new Coin(this);
        coin4.addCollisionListener(new Pickup(man, coin4));

        coin5 = new Coin(this);
        coin5.addCollisionListener(new Pickup(man, coin5));

        // make heart
        heart1 = new Heart(this);
        heart1.addCollisionListener(new Lives(man, heart1));
    }

    public abstract String getLevelName();
}
