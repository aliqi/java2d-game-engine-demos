package demo2;

import java2d.game.*;
import utils.PlayerInputs;

import java.awt.geom.Point2D;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/6 17:39
 */
public class Player extends GameComponent {

    public GameObject weapon;

    public double speed = 5.0;

    private double scale = 1d;

    private double rotation = 0;

    private int sign = 1;

    private Transform transform;

    private final Point2D axes = new Point2D.Double();

    private Inputs inputs;

    @Override
    protected void awake() {
        super.awake();
        inputs = getInputs();
        transform = getGameObject().transform;
    }

    @Override
    protected void update() {

        scale += sign * 0.1 * Time.deltaTime * 10;
        if (scale >= 3)
            sign = -1;
        if (scale <= 1)
            sign = 1;

        weapon.transform.setLocalScale(scale, scale);

        rotation += Time.deltaTime * 500;
        if (rotation >= 360) rotation %= 360;

        weapon.transform.setLocalRotation(rotation);

        PlayerInputs.setAxes(inputs, axes);

        double t = speed * Time.deltaTime * 100;
        transform.translate(Maths.multiple(axes, t));
    }
}
