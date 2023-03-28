package components;

import java2d.game.LineRender;
import java2d.game.Maths;
import java2d.game.Space;
import java2d.game.StringRender;

import java.awt.*;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;

public class RotateTowardsMouse extends LineRender {

    public double deltaRotation = 90;

    private StringRender stringRender;

    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public RotateTowardsMouse() {
        color = Color.yellow;
    }

    @Override
    protected void awake() {
        super.awake();
        stringRender = new StringRender();
        stringRender.setOrigin(0.5, 0.5);
        stringRender.setRenderOrder(100);
        getGameObject().addComponent(stringRender);
    }

    @Override
    protected void update() {
        Point2D mousePosition = getInputs().getWorldMousePosition();

        if (mousePosition != null) {
            Point2D position = getGameObject().transform.getPosition();
            Point2D direction = new Point2D.Double(mousePosition.getX() - position.getX(),
                    mousePosition.getY() - position.getY());

            Maths.normalize(direction);
            double rotation = Maths.toRotation(direction);
            stringRender.text = decimalFormat.format(rotation);
            getGameObject().transform.setLocalRotation(rotation + deltaRotation);

            space = Space.world;
            points.clear();
            points.add(position);
            points.add(mousePosition);
        }
    }
}
