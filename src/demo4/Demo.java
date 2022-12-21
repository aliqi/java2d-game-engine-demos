package demo4;

import components.EnemySpawner;
import components.ShipController;
import java2d.game.Game;
import java2d.game.GameObject;
import java2d.game.GameScene;
import java2d.game.Images;
import java2d.game.ui.Button;
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

        String info = "是否产生敌人：";
        Button button = new Button(Images.load("assets.sprites/ui/buttons/start_normal.png"));
        button.pressed = Images.load("assets.sprites/ui/buttons/start_pressed.png");
        button.hover = Images.load("assets.sprites/ui/buttons/start_hover.png");
        button.origin.setLocation(0, 1d);
        button.transform.setPosition(10, scene.ui.getHeight() - 10);
        button.text.content = info + spawner.enabled;
        button.text.color = Color.black;
        button.text.boundsVisible = true;
        button.text.font = new Font("微软雅黑", Font.PLAIN, 15);
        button.clickedEvent = b -> {
            spawner.enabled = !spawner.enabled;
            b.text.content = info + spawner.enabled;
        };
        scene.ui.add(button);
    }
}