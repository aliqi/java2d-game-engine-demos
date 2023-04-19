package components;

import java2d.game.GameComponent;
import java2d.game.Maths;
import java2d.game.Time;
import java2d.game.Transform;

import java.awt.geom.Point2D;

/**
 * Author:     Zhao Yan
 * DateTime:   2023/4/19 7:49
 */
public class DestinationTranslator extends GameComponent {

    public final Point2D destination = new Point2D.Double();

    public double speed = 1d;

    public ReachedEvent reachedEvent;

    public boolean rotateEnabled = true;

    public double rotationDelta = 0;

    private boolean moving;

    public void begin() {
        moving = true;
    }

    public void end() {
        moving = false;
    }

    @Override
    protected void update() {
        if (moving) {
            Transform transform = getTransform();
            Point2D position = transform.getPosition();

            if (Maths.distance(position, destination) <= 0.001) {
                transform.setPosition(destination);

                if (reachedEvent != null)
                    reachedEvent.reached(this);

                moving = false;
            } else {
                if (rotateEnabled) {
                    Point2D direction = Maths.subtract(destination, position);
                    transform.setLocalRotation(Maths.toRotation(direction) + rotationDelta);
                }
                
                position = Maths.moveTowards(position, destination,
                        speed * Time.deltaTime * 100);

                transform.setPosition(position);
            }
        }
    }

    @FunctionalInterface
    public interface ReachedEvent {

        void reached(DestinationTranslator translator);
    }
}
