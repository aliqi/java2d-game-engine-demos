package demo3;

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

    private final Point2D direction = new Point2D.Double(0, -1);

    private final GameScene scene;

    public PlayerInput(GameScene scene) {
        this.scene = scene;
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

        if (input.getX() != 0 || input.getY() != 0) {
            direction.setLocation(input);
            transform.setLocalRotation(Directions.toRotation(direction) + 90);
        }

        double t = speed * Time.deltaTime * 100;
        transform.translate(Directions.multiple(input, t));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Transform transform = getGameObject().transform;
        Point2D direction = Directions.toDirection(transform.getLocalRotation() - 90);
        Bullet bullet = new Bullet("assets.sprites/bullet1.png", direction, 0.5);
        bullet.transform.setPosition(transform.getPosition());
        scene.add(bullet);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouse released");
    }
}