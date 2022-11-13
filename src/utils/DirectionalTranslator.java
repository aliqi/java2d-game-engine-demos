package utils;

import java2d.game.GameComponent;
import java2d.game.Time;
import java2d.game.Transform;

import java.awt.geom.Point2D;

/**
 * Author: ZhaoYan
 * Created: 2022/11/13 14:16
 */
public class DirectionalTranslator extends GameComponent {

    public final Point2D direction = new Point2D.Double();

    public double speed = 1;

    @Override
    protected void update() {
        double t = speed * Time.deltaTime * 1000;
        Transform transform = getGameObject().transform;
        transform.setLocalRotation(Directions.toRotation(direction));
        transform.translate(Directions.multiple(direction, t));
    }
}
