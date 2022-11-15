package demo4;

import components.Enemy;
import components.ShipController;
import java2d.game.Game;
import java2d.game.GameObject;
import java2d.game.GameScene;
import components.EnemySpawner;
import models.Ship;
import utils.Global;

import java.awt.*;
import java.awt.geom.Point2D;

public class Demo {

    public static void main(String[] args) {
        Game game = new Game("Flight Edu");
        GameScene scene = game.getScene();
        Dimension renderSize = game.getRenderSize();

        // App-level singleton
        Global.map.put("game", game);

        // Create ship
        Ship ship = new Ship("player",
                new Point2D.Double(renderSize.getWidth() * 0.5, renderSize.getHeight() - 100));
        ship.transform.setLocalScale(2, 2);
        ShipController shipController = ship.getComponent(ShipController.class);
        shipController.verticalEnabled = false;
        shipController.rotateEnabled = false;
        scene.add(ship);

        // Create enemy
        Enemy enemy = new Enemy("enemy",
                new Point2D.Double(renderSize.getWidth() * 0.5, 100));
        scene.add(enemy);

        // Create spawner
        GameObject spawner = new GameObject("spawner");
        EnemySpawner enemySpawner = new EnemySpawner();
        spawner.addComponent(enemySpawner);
        scene.add(spawner);

        // Control the spawner
//        spawner.enabled = false;
    }
}
