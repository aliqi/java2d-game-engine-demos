package components;

import java2d.game.GameObject;
import java2d.game.Maths;
import java2d.game.Sprite;
import java2d.game.SpriteGameObject;

/**
 * Author: ZhaoYan
 * Created: 2023/3/13 15:49
 */
public class SpriteBar extends GameObject {

    private final SpriteGameObject barObj;

    public double getRatio() {
        return barObj.transform.getLocalScale().getX();
    }

    public void setRatio(double ratio) {
        barObj.transform.setLocalScale(Maths.clamp(ratio, 0d, 1d), barObj.transform.getLocalScale().getY());
    }

    public SpriteBar() {
        this("assets/sprites/bar_fill.png", "assets/sprites/bar_border.png");
    }

    public SpriteBar(String bar, String border) {
        this(Sprite.load(bar), Sprite.load(border));
    }

    public SpriteBar(Sprite bar, Sprite border) {
        barObj = new SpriteGameObject(bar, 0, 0.5);
        add(barObj);

        SpriteGameObject borderObj = new SpriteGameObject(border, 0, 0.5);
        add(borderObj);

        barObj.transform.setLocalPosition(-bar.getWidth() * 0.5, 0);
        borderObj.transform.setLocalPosition(-border.getWidth() * 0.5, 0);
    }
}
