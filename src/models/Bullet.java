package models;

import java2d.game.*;
import components.DirectionalTranslator;
import components.Lifetime;

import java.awt.geom.Point2D;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/7 17:24
 */
public class Bullet extends SpriteGameObject {

    public Bullet(String spritePath, Point2D direction, double lifetime) {
        super("bullet", spritePath, 0, 0.5);
        setRenderOrder(2);

        DirectionalTranslator translator = new DirectionalTranslator();
        translator.direction.setLocation(direction);
        addComponent(translator);

        Lifetime life = new Lifetime();
        life.time = lifetime;
        addComponent(life);

        transform.setLocalRotation(Maths.toRotation(direction));
    }
}
