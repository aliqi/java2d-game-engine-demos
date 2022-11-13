package demo2;

import java2d.game.*;
import utils.Directions;
import utils.PlayerInputs;

import java.awt.geom.Point2D;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/6 17:40
 */
public class PlayerInput extends GameComponent {

    public double speed = 5.0;

    private final Point2D input = new Point2D.Double();

    private Transform transform;

    @Override
    protected void awake() {
        super.awake();

        transform = getGameObject().transform;
    }

    @Override
    protected void update() {
        PlayerInputs.getAxes(input);

        double t = speed * Time.deltaTime * 100;
        transform.translate(Directions.multiple(input, t));
    }
}