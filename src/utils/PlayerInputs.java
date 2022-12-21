package utils;

import java2d.game.Inputs;

import java.awt.geom.Point2D;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/7 17:32
 */
public interface PlayerInputs {

    static void setAxes(Inputs inputs, Point2D direction) {
        double x = 0, y = 0;

        if (inputs.getKey('w')) y -= 1;
        if (inputs.getKey('s')) y += 1;
        if (inputs.getKey('a')) x -= 1;
        if (inputs.getKey('d')) x += 1;

        direction.setLocation(x, y);
    }
}
