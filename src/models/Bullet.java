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

public class Bullet extends SpriteGameObject implements CircleCollider.CollideEnterEvent,
        CircleCollider.CollidingEvent, CircleCollider.CollideExitEvent, DirectionalTranslator.ReachMaxDistance {

    public int damage = 10;

    public double getSpeed() {
        DirectionalTranslator translator = getComponent(DirectionalTranslator.class);
        return translator == null ? 0 : translator.speed;
    }

    public void setSpeed(double speed) {
        DirectionalTranslator translator = getComponent(DirectionalTranslator.class);

        if (translator != null)
            translator.speed = speed;
    }

    public double getMaxDistance() {
        DirectionalTranslator translator = getComponent(DirectionalTranslator.class);
        return translator == null ? 0 : translator.maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        DirectionalTranslator translator = getComponent(DirectionalTranslator.class);

        if (translator != null)
            translator.maxDistance = maxDistance;
    }

    public Bullet(String spritePath, Point2D direction, double lifetime) {
        super("bullet", spritePath, 0.5, 0.5);
        setRenderOrder(2);

        DirectionalTranslator translator = new DirectionalTranslator();
        // translator.speed = .3f;
        translator.reachMaxDistance = this;
        translator.direction.setLocation(direction);
        addComponent(translator);

        if (lifetime > 0)
            addComponent(new Lifetime(lifetime));

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.radius = 20;
        circleCollider.offset.setLocation(0, 0);
        circleCollider.collideEnterEvent = this;
        circleCollider.collideExitEvent = this;
        circleCollider.collidingEvent = this;
        circleCollider.visible = Game.debugEnabled;

        addComponent(circleCollider);

        transform.setLocalRotation(Maths.toRotation(direction));
    }

    public void fly() {
        DirectionalTranslator translator = getComponent(DirectionalTranslator.class);
        translator.begin();
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

    @Override
    public void reached(DirectionalTranslator translator) {
        destroy();
    }
}
