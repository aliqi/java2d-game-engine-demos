package models;

import components.AutoGun;
import components.CircleCollider;
import components.HP;
import components.ShipController;
import java2d.game.*;

import java.awt.*;
import java.awt.geom.Point2D;

public class Ship extends SpriteGameObject implements HP.ValueChanged, CircleCollider.CollideEnterEvent, CircleCollider.CollideExitEvent {

    public Ship(String name) {
        this(name, 0);
    }

    public Ship(String name, Point2D position) {
        this(name, 0);
        transform.setPosition(position);
    }

    public Ship(String name, int renderOrder) {
        this(name, "assets/sprites/spacecraft.png", 0.5, 0.5, renderOrder);
    }

    public Ship(String name, String spritePath, double ratioX, double ratioY, int renderOrder) {
        super(name, spritePath, ratioX, ratioY);

        setRenderOrder(1);

        LineRender lineRender = new LineRender();
        lineRender.space = Space.local;
        lineRender.points.add(new Point2D.Double());
        lineRender.points.add(new Point2D.Double(0, -20d));
        lineRender.color = Color.red;
        lineRender.stroke = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        lineRender.setRenderOrder(2);
        lineRender.visible = Game.debugEnabled;

        SortGroup sortGroup = new SortGroup();
        sortGroup.setRenderOrder(renderOrder);

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.radius = 20;
        circleCollider.collideEnterEvent = this;
        circleCollider.collideExitEvent = this;
        circleCollider.visible = Game.debugEnabled;

        addComponent(lineRender);
        addComponent(sortGroup);
        addComponent(new ShipController());
        addComponent(new AutoGun(false));
        addComponent(circleCollider);

        transform.setLocalScale(2, 2);

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
        other.color = Color.red;

        if (other.getGameObject().compareTag("enemy"))
            die();
    }

    @Override
    public void collideExit(CircleCollider other) {
        other.color = Color.green;
    }
}
