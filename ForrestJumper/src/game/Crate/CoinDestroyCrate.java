package game.Crate;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Coins.Coin;

public class CoinDestroyCrate implements CollisionListener {

    private final Coin coin;

    public CoinDestroyCrate(Coin coin) {
        this.coin = coin;
    }

    @Override
    public void collide(CollisionEvent destroy) {

        if (destroy.getOtherBody() == coin) {
            destroy.getReportingBody().destroy();
        }
    }
}