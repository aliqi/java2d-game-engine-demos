package models;

import components.CircleCollider;
import components.DirectionalTranslator;
import components.HP;
import components.Lifetime;
import java2d.game.Game;
import java2d.game.GameObject;
import java2d.game.Maths;
import java2d.game.SpriteGameObject;

import java.awt.geom.Point2D;

public class Bullet extends SpriteGameObject implements CircleCollider.CollideEnterEvent, CircleCollider.CollidingEvent, CircleCollider.CollideExitEvent {

    public int damage = 10;

    public Bullet(String spritePath, Point2D direction, double lifetime) {
        super("bullet", spritePath, 0, 0.5);
        setRenderOrder(2);

        DirectionalTranslator translator = new DirectionalTranslator();
//        translator.speed = .3f;
        translator.direction.setLocation(direction);
        addComponent(translator);

        Lifetime life = new Lifetime();
        life.time = lifetime;
        addComponent(life);

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.radius = 20;
        circleCollider.offset.setLocation(60, 0);
        circleCollider.collideEnterEvent = this;
        circleCollider.collideExitEvent = this;
        circleCollider.collidingEvent = this;
        circleCollider.visible = Game.debugEnabled;

        addComponent(circleCollider);

        transform.setLocalRotation(Maths.toRotation(direction));
    }

    @Override
    public void collideEnter(CircleCollider other) {
        GameObject target = other.getGameObject();

        if (target.compareTag("enemy")) {
            HP hp = target.getComponent(HP.class);
            if (hp != null)
                hp.setValue(hp.getValue() - damage);
            destroy();
        }
    }

    @Override
    public void collideExit(CircleCollider other) {
    }

    @Override
    public void colliding(CircleCollider other) {
//        System.out.println("colliding: " + other.getGameObject().name);
    }
}
