package demo4;

import components.EnemySpawner;
import components.ShipController;
import java2d.game.Game;
import java2d.game.GameObject;
import java2d.game.GameScene;
import models.Enemy;
import models.Ship;
import utils.Global;

import java.awt.*;
import java.awt.geom.Point2D;

public class Demo {

    public static void main(String[] args) {
        // Game.debugEnabled = true;

        Game game = new Game("Flight Edu");
        GameScene scene = game.getScene();
        Dimension renderSize = game.getRenderSize();

        // App-level singleton
        Global.map.put("game", game);

        // Create ship
        Ship ship = new Ship("player",
                new Point2D.Double(renderSize.getWidth() * 0.5, renderSize.getHeight() - 100));
        ShipController shipController = ship.getComponent(ShipController.class);
        shipController.verticalEnabled = false;
        shipController.rotateEnabled = false;
        scene.add(ship);

        // Create enemy
        Enemy enemy = new Enemy("enemy",
                new Point2D.Double(renderSize.getWidth() * 0.5, 400));
        scene.add(enemy);

        // Create spawner
        GameObject spawner = new GameObject("spawner");
        EnemySpawner enemySpawner = new EnemySpawner();
        spawner.addComponent(enemySpawner);
        scene.add(spawner);

        // Control the spawner
        spawner.enabled = true;
    }
}