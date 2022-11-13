package demo3;

import java2d.game.*;
import utils.DirectionalTranslator;
import utils.Directions;
import utils.Lifetime;

import java.awt.geom.Point2D;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/7 17:24
 */
public class Bullet extends SpriteGameObject {

    public Bullet(String spritePath, Point2D direction, double lifetime) {
        super("bullet", spritePath, 0, 0.5);
        setOrder(2);

        DirectionalTranslator translator = new DirectionalTranslator();
        translator.direction.setLocation(direction);
        addComponent(translator);

        Lifetime life = new Lifetime();
        life.time = lifetime;
        addComponent(life);

        transform.setLocalRotation(Directions.toRotation(direction));
    }
}
