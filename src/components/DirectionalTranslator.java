package components;

import java2d.game.GameComponent;
import java2d.game.Maths;
import java2d.game.Time;
import java2d.game.Transform;

import java.awt.geom.Point2D;

public class DirectionalTranslator extends GameComponent {

    public final Point2D direction = new Point2D.Double();

    public ReachMaxDistance reachMaxDistance;

    public double speed = 1d;

    public double maxDistance;

    public boolean rotateEnabled = true;

    private Point2D beginPosition;

    public DirectionalTranslator() {
        enabled = false;
    }

    public void begin() {
        beginPosition = getGameObject().transform.getPosition();
        enabled = true;
    }

    public void end() {
        enabled = false;
        beginPosition = null;
    }

    @Override
    protected void update() {
        double t = speed * Time.deltaTime * 1000;
        Transform transform = getGameObject().transform;

        if (rotateEnabled)
            transform.setLocalRotation(Maths.toRotation(direction));

        Point2D movement = Maths.multiple(direction, t);

        if (beginPosition != null && maxDistance > 0) {
            Point2D position = transform.getPosition();
            position.setLocation(position.getX() + movement.getX(),
                    position.getY() + movement.getY());

            if (position.distance(beginPosition) >= maxDistance) {
                Point2D dir = new Point2D.Double(direction.getX(), direction.getY());
                Maths.normalize(dir);
                dir = Maths.multiple(dir, maxDistance);
                position.setLocation(beginPosition.getX() + dir.getX(), beginPosition.getY() + dir.getY());
                transform.setPosition(position);

                if (reachMaxDistance != null)
                    reachMaxDistance.reached(this);

                beginPosition = null;
                end();

                return;
            }
        }

        transform.translate(movement);
    }

    public interface ReachMaxDistance {

        void reached(DirectionalTranslator translator);
    }
}
