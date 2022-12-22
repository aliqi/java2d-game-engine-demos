package components;

import java2d.game.LineRender;
import java2d.game.Maths;
import java2d.game.Time;
import java2d.game.Transform;

import java.awt.*;
import java.awt.geom.Point2D;

public class CircleDrawer extends LineRender {

    public double gap = 4f;

    public double interval = 0.01;

    public double radius = 100;

    public double startRotation = -90;

    public boolean rotateEnabled = false;

    private double rotation = 0;

    private double timer;

    private Transform transform;

    public CircleDrawer() {
        color = Color.red;
    }

    @Override
    protected void awake() {
        timer = interval;
        transform = getTransform();
    }

    private void addCurrentPoint() {
        Point2D direction = Maths.multiple(Maths.toDirection(rotation + startRotation), radius);
        points.add(Maths.add(transform.getPosition(), direction));
    }

    @Override
    protected void update() {
        rotate();
    }

    private void rotate() {
        if (timer >= interval) {
            timer = 0;
            addCurrentPoint();

            rotation += gap;
            if (rotation >= 360)
                rotation %= 360;

            if (rotateEnabled)
                transform.setLocalRotation(rotation);
        } else
            timer += Time.deltaTime;
    }
}
