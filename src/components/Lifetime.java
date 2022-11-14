package components;

import java2d.game.Game;
import java2d.game.GameComponent;
import java2d.game.Time;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/7 18:01
 */
public class Lifetime extends GameComponent {

    public double time = 1;

    private double awakeTime;

    @Override
    protected void destroy() {
        super.destroy();
        Game.debug("Lifetime over");
    }

    @Override
    protected void update() {
        awakeTime += Time.deltaTime;
        if (awakeTime >= time)
            getGameObject().destroy();
    }
}
