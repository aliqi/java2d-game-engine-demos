package components;

import java2d.game.*;
import utils.PlayerInputs;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/6 17:40
 */
public class ShipController extends GameComponent implements GameMouseEvent {

    public double speed = 5.0;

    public boolean verticalEnabled = true;

    public boolean rotateEnabled = true;

    private final Point2D input = new Point2D.Double();

    private final Point2D direction = new Point2D.Double(0, -1);

    public ShipController() {
    }

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

        if (!verticalEnabled) {
            input.setLocation(input.getX(), 0);
        }

        if (input.getX() != 0 || input.getY() != 0) {
            Rotator rotator = gameObject.getComponent(Rotator.class);

            if (rotator != null)
                rotator.enabled = false;

            direction.setLocation(input);

            if (rotateEnabled)
                transform.setLocalRotation(Maths.toRotation(direction) + 90);
        }

        double t = speed * Time.deltaTime * 100;
        transform.translate(Maths.multiple(input, t));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        AutoGun gun = getGameObject().getComponent(AutoGun.class);

        if (gun != null)
            gun.enabled = !gun.enabled;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouse released");
    }
}