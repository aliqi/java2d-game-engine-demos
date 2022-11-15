package models;

import components.AutoGun;
import components.CircleCollider;
import components.HP;
import components.ShipController;
import java2d.game.LineRender;
import java2d.game.SortGroup;
import java2d.game.SpriteGameObject;

import java.awt.*;
import java.awt.geom.Point2D;

public class Ship extends SpriteGameObject implements HP.ValueChanged, CircleCollider.CollideEnterEvent {

    public Ship(String name) {
        this(name, 0);
    }

    public Ship(String name, Point2D position) {
        this(name, 0);
        transform.setPosition(position);
    }

    public Ship(String name, int renderOrder) {
        this(name, "assets.sprites/spacecraft.png", 0.5, 0.5, renderOrder);
    }

    public Ship(String name, String spritePath, double ratioX, double ratioY, int renderOrder) {
        super(name, spritePath, ratioX, ratioY);

        setRenderOrder(1);

        Point2D origin = transform.getOrigin();

        LineRender lineRender = new LineRender();
        lineRender.points.add(origin);
        lineRender.points.add(new Point2D.Double(origin.getX(), origin.getY() - 20f));
        lineRender.color = Color.red;
        lineRender.stroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        lineRender.setRenderOrder(2);

        SortGroup sortGroup = new SortGroup();
        sortGroup.setRenderOrder(renderOrder);

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.radius = 40;
        circleCollider.collideEnterEvent = this;

        addComponent(lineRender);
        addComponent(sortGroup);
        addComponent(new ShipController());
        addComponent(new AutoGun(false));
        addComponent(circleCollider);

        tag = "player";
    }

    @Override
    public void changed(int newValue, int oldValue) {
        if (newValue == 0)
            die();
    }

    private void die() {
        Explosion.explode(transform.getPosition());
        destroy();
    }

    @Override
    public void collideEnter(CircleCollider other) {
        if (other.getGameObject().compareTag("enemy"))
            die();
    }
}
