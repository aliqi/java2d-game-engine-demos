package components;

import java2d.game.GameComponent;
import java2d.game.Time;
import java2d.game.Transform;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/8 15:00
 */
public class Rotator extends GameComponent {

    public Transform target;

    public double speed = 1;

    private double rotation = 0;

    @Override
    protected void awake() {
        if (target == null)
            target = getGameObject().transform;
    }

    @Override
    protected void update() {
        if (target != null) {
            rotation += Time.deltaTime * 100 * speed;
            if (rotation >= 360) rotation %= 360;

            target.setLocalRotation(rotation);
        }
    }
}
