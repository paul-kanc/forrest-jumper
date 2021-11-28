package game;

import city.cs.engine.*;
import city.cs.engine.UserView;
import game.Coins.Pickup;
import game.Levels.*;
import javax.swing.*;
import java.awt.*;

/**
 * Adds images to the foreground and background of the view of the simulation.
 */
public class GameView extends UserView {

    private static Image background, instructions, level1, level2, level3, level4, level5, level6, level7, level8, level9, level10,  heart, heart1,
            heart2, heart3, heart4, heart5, coin, x, zero, one, two, three, four, five, paused, win, lose, blank;

    public static void setInstructions(Image text) {
        GameView.instructions = text;
    }

    public static Image getLevel1() {
        return level1;
    }

    public static Image getLevel2() {
        return level2;
    }

    public static Image getLevel3() {
        return level3;
    }

    public static Image getLevel4() {
        return level4;
    }

    public static Image getLevel5() {
        return level5;
    }

    public static Image getLevel6() {
        return level6;
    }

    public static Image getLevel7() {
        return level7;
    }

    public static Image getLevel8() {
        return level8;
    }

    public static Image getLevel9() {
        return level9;
    }

    public static Image getLevel10() {
        return level10;
    }

    public static void setBackground(Image background) {
        GameView.background = background;
    }

    public static void setHeart1(Image heart1) {
        GameView.heart1 = heart1;
    }

    public static void setHeart2(Image heart2) {
        GameView.heart2 = heart2;
    }

    public static void setHeart3(Image heart3) {
        GameView.heart3 = heart3;
    }

    public static void setHeart4(Image heart4) {
        GameView.heart4 = heart4;
    }

    public static void setHeart5(Image heart5) {
        GameView.heart5 = heart5;
    }

    public static Image getHeart() {
        return heart;
    }

    public static Image getZero() {
        return zero;
    }

    public static Image getOne() {
        return one;
    }

    public static Image getTwo() {
        return two;
    }

    public static Image getThree() {
        return three;
    }

    public static Image getFour() {
        return four;
    }

    public static Image getFive() {
        return five;
    }

    public static Image getPaused() {
        return paused;
    }

    public static void setCoin(Image coin) {
        GameView.coin = coin;
    }

    public static void setX(Image x) {
        GameView.x = x;
    }

    public static Image getWin() {
        return win;
    }

    public static Image getLose() {
        return lose;
    }

    public static Image getBlank() {
        return blank;
    }

    /**
     * Hold the images for the world that is used in the constructor.
     * @param w the world that needs the foreground and background edited.
     * @param width the width of the screen.
     * @param height the height of the screen.
     */
    public GameView(World w, int width, int height) {
        super(w, width, height);
        blank = new ImageIcon("data/blank.png").getImage();
        level1 = new ImageIcon ("data/level1text.png").getImage();
        level2 = new ImageIcon ("data/level2text.png").getImage();
        level3 = new ImageIcon ("data/level3text.png").getImage();
        level4 = new ImageIcon ("data/level4text.png").getImage();
        level5 = new ImageIcon ("data/level5text.png").getImage();
        level6 = new ImageIcon ("data/level6text.png").getImage();
        level7 = new ImageIcon ("data/level7text.png").getImage();
        level8 = new ImageIcon ("data/level8text.png").getImage();
        level9 = new ImageIcon ("data/level9text.png").getImage();
        level10 = new ImageIcon ("data/level10text.png").getImage();
        heart = new ImageIcon("data/heart.gif").getImage();
        zero = new ImageIcon("data/0.png").getImage();
        one = new ImageIcon("data/1.png").getImage();
        two = new ImageIcon("data/2.png").getImage();
        three = new ImageIcon("data/3.png").getImage();
        four = new ImageIcon("data/4.png").getImage();
        five = new ImageIcon("data/5.png").getImage();
        paused = new ImageIcon("data/paused.png").getImage();
        win = new ImageIcon("data/win.png").getImage();
        lose = new ImageIcon("data/lose.png").getImage();

        Pickup.setNumber(zero);
    }

    @Override
    protected void paintBackground(Graphics2D image) {

        if (Game.getLevel() instanceof GameLevelIntro) {
            background = new ImageIcon("data/jungleBlur.png").getImage();
        }

        if (Game.getLevel() instanceof GameLevel1) {
            background = new ImageIcon("data/jungle.png").getImage();
        }

        else if (Game.getLevel() instanceof GameLevel2) {
            background = new ImageIcon("data/jungle2.png").getImage();
        }

        else if (Game.getLevel() instanceof GameLevel3) {
            background = new ImageIcon("data/jungle3.png").getImage();
        }

        else if (Game.getLevel() instanceof GameLevel4) {
            background = new ImageIcon("data/jungle4.png").getImage();
        }

        else if (Game.getLevel() instanceof GameLevel5) {
            background = new ImageIcon("data/jungle5.png").getImage();
        }

        else if (Game.getLevel() instanceof GameLevel6) {
            background = new ImageIcon("data/jungle6.png").getImage();
        }

        else if (Game.getLevel() instanceof GameLevel7) {
            background = new ImageIcon("data/jungle7.png").getImage();
        }

        else if (Game.getLevel() instanceof GameLevel8) {
            background = new ImageIcon("data/jungle8.png").getImage();
        }

        else if (Game.getLevel() instanceof GameLevel9) {
            background = new ImageIcon("data/jungle9.png").getImage();
        }

        else if (Game.getLevel() instanceof GameLevel10) {
            background = new ImageIcon("data/jungle10.png").getImage();
        }

        else {
            background = new ImageIcon("data/menu.png").getImage();
        }

        image.drawImage(background, -50, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D image) {
        image.drawImage(instructions, 100, 25, this);
        image.drawImage(coin, 30, 25, 30, 40, this);
        image.drawImage(x, 70, 32, this);
        image.drawImage(Pickup.getNumber(), 100, 28, 22, 31, this);
        image.drawImage(heart1, 1125, 25, 51, 40, this);
        image.drawImage(heart2, 1065, 25, 51, 40, this);
        image.drawImage(heart3, 1005, 25, 51, 40, this);
        image.drawImage(heart4, 945, 25, 51, 40, this);
        image.drawImage(heart5, 885, 25, 51, 40, this);
    }
}

//text box:3.3 x 12.74
//text size:20
//font:pixel non-bold
//effect:bold