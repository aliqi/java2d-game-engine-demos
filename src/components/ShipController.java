package components;

import java2d.game.*;
import models.Dot;
import utils.PlayerInputs;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class ShipController extends GameComponent implements GameMouseEvent {

    public double speed = 2.0;

    public boolean horizontalEnabled = true;

    public boolean verticalEnabled = true;

    public boolean rotateEnabled = true;

    public boolean shootContinues = true;

    private final Point2D axes = new Point2D.Double();

    private final Point2D direction = new Point2D.Double(0, -1);

    private Inputs inputs;

    public ShipController() {
    }

    @Override
    protected void awake() {
        super.awake();

        inputs = getInputs();
        inputs.addMouseEventListener(this);

        Dot dot = new Dot();
        getGameObject().add(dot);
        System.out.println(dot.transform.getPosition());
        System.out.println(getGameObject().transform.getPosition());
    }

    @Override
    protected void destroy() {
        super.destroy();
        inputs.removeMouseEventListener(this);
    }

    @Override
    protected void update() {
        PlayerInputs.setAxes(inputs, axes);
        Maths.normalize(axes);

        GameObject gameObject = getGameObject();
        Transform transform = gameObject.transform;

        if (!verticalEnabled)
            axes.setLocation(axes.getX(), 0);

        if (!horizontalEnabled)
            axes.setLocation(0, axes.getY());

        if (axes.getX() != 0 || axes.getY() != 0) {
            Rotator rotator = gameObject.getComponent(Rotator.class);

            if (rotator != null)
                rotator.enabled = false;

            direction.setLocation(axes);

            if (rotateEnabled)
                transform.setLocalRotation(Maths.toRotation(direction) + 90);
        }

        double t = speed * Time.deltaTime * 100;
        transform.translate(Maths.multiple(axes, t));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (enabled) {
            AutoGun gun = getGameObject().getComponent(AutoGun.class);

            if (gun != null) {
                if (shootContinues)
                    gun.enabled = !gun.enabled;
                else {
                    gun.enabled = false;
                    gun.shoot();
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}