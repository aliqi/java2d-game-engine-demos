package components;

import java2d.game.GameComponent;
import java2d.game.GameObject;
import java2d.game.Maths;

import java.awt.geom.Point2D;

/**
 * Author: ZhaoYan
 * Created: 2023/3/28 21:38
 */
public class Follower extends GameComponent {

    public GameObject target;

    public final Point2D offset = new Point2D.Double();

    public Follower() {
    }

    public Follower(GameObject target) {
        this.target = target;
    }

    public Follower(GameObject target, double offsetX, double offsetY) {
        this(target);
        offset.setLocation(offsetX, offsetY);
    }

    @Override
    protected void lateUpdate() {
        if (!isDestroyed(target))
            getTransform().setPosition(Maths.subtract(target.transform.getPosition(), offset));
    }
}
