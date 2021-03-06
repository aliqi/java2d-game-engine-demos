package demo2;

import java2d.game.*;
import utils.Directions;
import utils.PlayerInputs;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/6 17:40
 */
public class PlayerInput extends GameComponent implements GameMouseEvent {

    public double speed = 5.0;

    private final Point2D input = new Point2D.Double();

    @Override
    protected void awake() {
        super.awake();
        Inputs.addMouseEventListener(this);
    }

    @Override
    protected void destroy() {
        super.destroy();
        Inputs.removeMouseEventListener(this);
    }

    @Override
    protected void update() {
        PlayerInputs.getAxes(input);

        GameObject gameObject = getGameObject();
        Transform transform = gameObject.transform;

        double t = speed * Time.deltaTime * 100;
        transform.translate(Directions.multiple(input, t));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouse pressed");
    }
}