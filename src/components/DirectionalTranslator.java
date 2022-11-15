package components;

import java2d.game.GameComponent;
import java2d.game.Maths;
import java2d.game.Time;
import java2d.game.Transform;

import java.awt.geom.Point2D;

public class DirectionalTranslator extends GameComponent {

    public Point2D direction = new Point2D.Double();

    public double speed = 1;

    public boolean rotateEnabled = true;

    @Override
    protected void update() {
        double t = speed * Time.deltaTime * 1000;
        Transform transform = getGameObject().transform;

        if (rotateEnabled)
            transform.setLocalRotation(Maths.toRotation(direction));

        transform.translate(Maths.multiple(direction, t));
    }
}
