package models;

import components.CircleDrawer;
import java2d.game.GameObject;

import java.awt.*;

public class CirclePainter extends GameObject {

    public final CircleDrawer drawer = new CircleDrawer();

    public CirclePainter() {
        addComponent(drawer);
    }

    public CirclePainter(Color color) {
        this();
        drawer.color = color;
    }
}
