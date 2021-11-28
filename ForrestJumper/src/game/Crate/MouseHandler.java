package game.Crate;

import city.cs.engine.*;
import game.ForegroundController;
import game.Game;
import game.Levels.GameLevel;
import game.Levels.GameLevelIntro;
import game.Levels.GameLevelLose;
import game.Levels.GameLevelWin;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private final WorldView view;
    public static Crate box;

    public MouseHandler(WorldView view) {
        this.view = view;
    }

    public void mousePressed(MouseEvent createBox) {
        if ((!(Game.getLevel() instanceof GameLevelIntro || Game.getLevel() instanceof GameLevelWin || Game.getLevel() instanceof GameLevelLose)) &&
                !(ForegroundController.getPaused())) {
            box = new Crate(view.getWorld());
            box.setPosition(view.viewToWorld(createBox.getPoint()));
            box.addImage(Crate.getImage());
            box.addCollisionListener(new CoinDestroyCrate(GameLevel.getCoin1()));
            box.addCollisionListener(new CoinDestroyCrate(GameLevel.getCoin2()));
            box.addCollisionListener(new CoinDestroyCrate(GameLevel.getCoin3()));
            box.addCollisionListener(new CoinDestroyCrate(GameLevel.getCoin4()));
            box.addCollisionListener(new CoinDestroyCrate(GameLevel.getCoin5()));
            box.addCollisionListener(new ReaperDestroyCrate(GameLevel.getReaper1()));
            box.addCollisionListener(new ReaperDestroyCrate(GameLevel.getReaper2()));
            box.addCollisionListener(new ReaperDestroyCrate(GameLevel.getReaper3()));
            box.addCollisionListener(new HeartDestroyCrate(GameLevel.getHeart1()));
        }
    }
}