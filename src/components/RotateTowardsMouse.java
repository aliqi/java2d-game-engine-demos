package components;

import java2d.game.Inputs;
import java2d.game.LineRender;
import java2d.game.Maths;
import java2d.game.StringRender;

import java.awt.*;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;

public class RotateTowardsMouse extends LineRender {

    private StringRender stringRender;

    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public RotateTowardsMouse() {
        color = Color.yellow;
    }

    @Override
    protected void awake() {
        super.awake();
        stringRender = getComponent(StringRender.class);
    }

    @Override
    protected void update() {
        Point2D mousePosition = Inputs.getMousePosition();

        if (mousePosition != null) {
            Point2D position = getGameObject().transform.getPosition();
            Point2D direction = new Point2D.Double(mousePosition.getX() - position.getX(),
                    mousePosition.getY() - position.getY());

            Maths.normalize(direction);
            double rotation = Maths.toRotation(direction);
            stringRender.text = decimalFormat.format(rotation);
            getGameObject().transform.setLocalRotation(rotation + 90);

            points.clear();
            points.add(position);
            points.add(mousePosition);
        }
    }
}
