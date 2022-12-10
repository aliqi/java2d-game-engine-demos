package components;

import java2d.game.*;
import models.Bullet;
import utils.Global;

import java.awt.geom.Point2D;

public class AutoGun extends GameComponent {

    public double interval = 0.2;

    public boolean mouseDistanceLimited = false;

    public double bulletLifetime = 1;

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
        double lifetime = bulletLifetime;

        if (mouseDistanceLimited)
            lifetime = 0;

        Bullet bullet = new Bullet("assets.sprites/bullet1.png", direction, lifetime);
        bullet.transform.setPosition(ship.getPosition());

        game.getScene().add(bullet);

        if (mouseDistanceLimited)
            bullet.setMaxDistance(Inputs.getMousePosition().distance(bullet.transform.getPosition()));

        bullet.fly();
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
