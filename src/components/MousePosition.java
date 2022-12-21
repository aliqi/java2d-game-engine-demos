package components;

import java2d.game.StringRender;
import java2d.game.Unique;

import java.awt.geom.Point2D;
import java.text.DecimalFormat;

@Unique
public class MousePosition extends StringRender {

    public final Point2D offset = new Point2D.Double(15, 0);

    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    protected void update() {
        Point2D mousePosition = getInputs().getMousePosition();

        if (mousePosition != null) {
            text = decimalFormat.format(mousePosition.getX()) + ", " + decimalFormat.format(mousePosition.getY());
            mousePosition.setLocation(mousePosition.getX() + offset.getX(),
                    mousePosition.getY() + offset.getY());
            getGameObject().transform.setPosition(mousePosition);
        }
    }
}
