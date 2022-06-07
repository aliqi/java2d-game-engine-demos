package demo2;

import java2d.game.GameComponent;
import java2d.game.GameObject;
import java2d.game.Time;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/6 17:39
 */
public class Player extends GameComponent {

    public GameObject weapon;

    private double scale = 1d;

    private double rotation = 0;

    private int sign = 1;

    @Override
    protected void update() {

        scale += sign * 0.1 * Time.deltaTime * 10;
        if (scale >= 1.2)
            sign = -1;
        if (scale <= 1)
            sign = 1;

        scale = 2f;

        weapon.transform.setLocalScale(scale, scale);

        rotation += Time.deltaTime * 500;
        if (rotation >= 360) rotation %= 360;

        weapon.transform.setLocalRotation(rotation);
    }
}
