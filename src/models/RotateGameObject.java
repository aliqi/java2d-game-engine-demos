package models;

import components.Rotator;
import java2d.game.GameObject;

public class RotateGameObject extends GameObject {

    public final Rotator rotator = new Rotator();

    public RotateGameObject() {
        addComponent(rotator);
    }

    public RotateGameObject(double speed) {
        this();
        rotator.speed = speed;
    }
}
