package game.Crate;

import city.cs.engine.*;

public class Crate extends DynamicBody {

    private static final Shape boxShape = new PolygonShape(
            -2,2, 2,2, 2,-2, -2,-2
    );

    private static final BodyImage image =
            new BodyImage("data/crate.png", 4);

    public Crate(World world) {
        super(world, boxShape);
    }

    public static BodyImage getImage() {
        return image;
    }
}