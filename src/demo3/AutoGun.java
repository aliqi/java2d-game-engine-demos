package demo3;

import java2d.game.Game;
import java2d.game.GameComponent;
import java2d.game.Time;
import java2d.game.Transform;
import utils.Directions;
import utils.Global;

import java.awt.geom.Point2D;

/**
 * Author: ZhaoYan
 * Created: 2022/11/13 14:48
 */
public class AutoGun extends GameComponent {

    public double interval = 0.1;

    private double lastShootTime;

    private Transform ship;

    private Game game;

    public AutoGun() {
        lastShootTime = interval;
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
        Point2D direction = Directions.toDirection(ship.getLocalRotation() - 90);
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
