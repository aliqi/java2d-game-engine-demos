package components;

import java2d.game.GameScene;
import java2d.game.GraphicsRender;
import java2d.game.Transform;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class CircleCollider extends GraphicsRender {

    public CollideEnterEvent collideEnterEvent;

    public CollideExitEvent collideExitEvent;

    public CollidingEvent collidingEvent;

    public int radius = 10;

    public final Point2D offset = new Point2D.Double();

    public boolean renderable = true;

    public Color renderColor = Color.green;

    private static final List<CircleCollider> colliders = new CopyOnWriteArrayList<>();

    private final Set<CircleCollider> collidings = new HashSet<>();

    private Transform transform;

    @Override
    protected void awake() {
        super.awake();
        colliders.add(this);
        transform = getGameObject().transform;
    }

    @Override
    protected void destroy() {
        colliders.remove(this);
        clean();
        super.destroy();
    }

    private void clean() {
        for (CircleCollider c : collidings)
            raiseCollideExit(c);

        collidings.clear();

        collideEnterEvent = null;
        collidingEvent = null;
        collideExitEvent = null;

        for (CircleCollider c : colliders) {
            if (c.collidings.contains(this)) {
                c.collidings.remove(this);
                c.raiseCollideExit(this);
            }
        }
    }

    @Override
    protected void update() {
        for (CircleCollider c : colliders) {
            if (c != this) {
                Point2D cp = c.transform.getPosition();
                cp.setLocation(cp.getX() + c.offset.getX(), cp.getY() + c.offset.getY());

                Point2D p = transform.getPosition();
                p.setLocation(p.getX() + offset.getX(), p.getY() + offset.getY());

                checkCollide(c, p.distance(cp));
            }
        }
    }

    private void checkCollide(CircleCollider c, double distance) {
        if (distance <= c.radius + radius) {
            // collided
            if (collidings.contains(c)) {
                raiseCollidingEvent(c);
            } else {
                collidings.add(c);
                raiseCollideEnter(c);
            }
        } else {
            if (collidings.contains(c)) {
                collidings.remove(c);
                raiseCollideExit(c);
            }
        }
    }

    private void raiseCollideExit(CircleCollider c) {
        if (collideExitEvent != null)
            collideExitEvent.collideExit(c);
    }

    private void raiseCollideEnter(CircleCollider c) {
        if (collideEnterEvent != null)
            collideEnterEvent.collideEnter(c);
    }

    private void raiseCollidingEvent(CircleCollider c) {
        if (collidingEvent != null)
            collidingEvent.colliding(c);
    }

    @Override
    protected void render(GameScene scene, Graphics2D g) {
        if (renderable) {
            Point2D position = transform.getPosition();
            int length = radius * 2;
            int x = (int) position.getX();
            int y = (int) position.getY();
            Color color = g.getColor();

            g.setColor(renderColor);

            g.drawOval((int) (x - radius + offset.getX()), (int) (y - radius + offset.getY()), length, length);

            g.setColor(color);
        }
    }

    @FunctionalInterface
    public interface CollideEnterEvent {
        void collideEnter(CircleCollider other);
    }

    @FunctionalInterface
    public interface CollideExitEvent {
        void collideExit(CircleCollider other);
    }

    @FunctionalInterface
    public interface CollidingEvent {
        void colliding(CircleCollider other);
    }
}
