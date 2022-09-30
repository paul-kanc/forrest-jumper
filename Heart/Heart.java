package game.Heart;

import city.cs.engine.*;

public class Heart extends DynamicBody {
    public Heart(World w) {
        super (w);

        Shape bodyShape = new PolygonShape(-0.456f,0.497f, 0.458f,0.497f, 0.634f,0.319f, 0.636f,0.004f, 0.639f,-1.063f, -0.641f,-1.065f, -0.639f,0.314f);
        SolidFixture bodyFixture = new SolidFixture(this, bodyShape, 9999);
        addImage(new BodyImage("data/heart.gif", 1));
    }
}
