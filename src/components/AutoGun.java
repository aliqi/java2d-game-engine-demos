package components;

import java2d.game.*;
import models.Bullet;
import utils.Global;

import java.awt.geom.Point2D;

public class AutoGun extends GameComponent {

    public double interval = .2;

    private double lastShootTime;

    private Transform ship;

    private Game game;

    public AutoGun() {
        lastShootTime = interval;
    }

    public AutoGun(boolean enabled) {
        this();
        this.enabled = enabled;
    }

    public AutoGun(double interval) {
        this.interval = interval;
        lastShootTime = interval;
    }

    @Override
    protected void awake() {
        super.awake();
        game = (Game) Global.map.get("game");
        ship = getGameObject().transform;
    }

    public void shoot() {
        Point2D direction = Maths.toDirection(ship.getLocalRotation() - 90);
        Bullet bullet = new Bullet("assets.sprites/bullet1.png", direction, 1);
        bullet.transform.setPosition(ship.getPosition());
        game.getScene().add(bullet);
    }

    @Override
    protected void update() {
        lastShootTime += Time.deltaTime;

        if (lastShootTime >= interval) {
            shoot();
            lastShootTime = 0;
        }
    }
}
