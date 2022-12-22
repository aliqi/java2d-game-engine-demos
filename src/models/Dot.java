package models;

import java2d.game.SpriteGameObject;

import java.awt.geom.Point2D;

public class Dot extends SpriteGameObject {

    public Dot() {
        super("dot", "assets/sprites/dot.png", 0.5, 0.5);
        setRenderOrder(99);
    }

    public static Dot create(double x, double y) {
        Dot dot = new Dot();
        dot.transform.setPosition(x, y);
        return dot;
    }

    public static Dot create(Point2D position) {
        return create(position.getX(), position.getY());
    }
}
