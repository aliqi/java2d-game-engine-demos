package components;

import java2d.game.CircleRender;
import java2d.game.Transform;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class CircleCollider extends CircleRender {

    public CollideEnterEvent collideEnterEvent;

    public CollideExitEvent collideExitEvent;

    public CollidingEvent collidingEvent;

    private static final List<CircleCollider> colliders = new CopyOnWriteArrayList<>();

    private final Set<CircleCollider> collidings = new HashSet<>();

    private Transform transform;

    public CircleCollider() {
        color = Color.green;
        setRenderOrder(99);
    }

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
        AffineTransform gt = transform.getGlobalTransform();

        for (CircleCollider c : colliders) {
            if (c != this) {
                Transform ct = c.transform;
                AffineTransform cgt = ct.getGlobalTransform();
                Point2D cp = cgt.transform(c.offset, null);
                Point2D p = gt.transform(offset, null);
                double distance = p.distance(cp);
                double total = Math.abs(c.radius * ct.getLocalScale().getX()) +
                        Math.abs(radius * transform.getLocalScale().getX());

                checkCollide(c, distance, total);
            }
        }
    }

    private void checkCollide(CircleCollider c, double distance, double total) {
        if (distance <= total) {
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
