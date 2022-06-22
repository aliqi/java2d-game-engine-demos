package demo3;

import java2d.game.*;
import utils.Directions;
import utils.Lifetime;

import java.awt.geom.Point2D;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/7 17:24
 */
public class Bullet extends GameObject {

    public Bullet(String spritePath, Point2D direction) {
        SpriteRender render = new SpriteRender();
        Sprite sprite = Sprite.load(spritePath);
        render.sprite = sprite;
        render.setOrder(2);
        addComponent(render);

        DirectionalBulletScript script = new DirectionalBulletScript();
        script.direction.setLocation(direction);
        addComponent(script);

        Lifetime lifetime = new Lifetime();
        lifetime.time = .5;
        addComponent(lifetime);

        transform.setOrigin(sprite.getWidth() * 0.5, 0);
        transform.setLocalRotation(Directions.toRotation(direction));
    }
}

class DirectionalBulletScript extends GameComponent {

    public final Point2D direction = new Point2D.Double();

    public double speed = 1;

    @Override
    protected void update() {
        double t = speed * Time.deltaTime * 500;
        Transform transform = getGameObject().transform;
        transform.setLocalRotation(Directions.toRotation(direction));
        transform.translate(Directions.multiple(direction, t));
    }
}
