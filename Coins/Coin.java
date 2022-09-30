package game.Coins;

import city.cs.engine.*;

public class Coin extends DynamicBody {

    public Coin(World w) {
        super (w);

        Shape bodyShape = new PolygonShape(-0.372f,0.5f, 0.392f,0.5f, 0.392f,-1.096f, -0.372f,-1.096f);
        SolidFixture bodyFixture = new SolidFixture(this, bodyShape, 9999);
        addImage(new BodyImage("data/coin.png", 1));
    }
}