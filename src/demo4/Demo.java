package demo4;

import components.EnemySpawner;
import components.ShipController;
import java2d.game.*;
import java2d.game.ui.Button;
import models.Enemy;
import models.Ship;

import java.awt.*;
import java.awt.geom.Point2D;

public class Demo {

    public static void main(String[] args) {
        Game.debugEnabled = true;

        Game game = new Game("Flight Edu");
        GameScene scene = game.getScene();

        // Create ship
        Ship ship = new Ship("player", 100, new Point2D.Double(0, 200));
        ShipController shipController = ship.getComponent(ShipController.class);
        shipController.verticalEnabled = false;
        shipController.rotateEnabled = false;
        scene.add(ship);

        // Create enemy
        Enemy enemy = new Enemy("enemy", new Point2D.Double(0, -200));
        scene.add(enemy);

        // Create spawner
        GameObject spawner = new GameObject("spawner");
        EnemySpawner enemySpawner = new EnemySpawner();
        enemySpawner.maxSpawnInterval = 1.0;
        spawner.addComponent(enemySpawner);
        scene.add(spawner);

        // Control the spawner
        spawner.enabled = true;

        String info = "是否产生敌人：";
        Button button = new Button(Images.load("assets/ui/buttons/start_normal.png"));
        button.pressed = Images.load("assets/ui/buttons/start_pressed.png");
        button.hover = Images.load("assets/ui/buttons/start_hover.png");
        button.origin.setLocation(0, 1d);
        button.transform.setPosition(10, scene.ui.getHeight() - 10);
        button.text.color = Color.white;
        button.text.offset.setLocation(0, -5);
        button.text.font = new Font("微软雅黑", Font.PLAIN, 16);
        button.text.content = info + spawner.enabled;
        button.clickedEvent = (b, mouseButton) -> {
            spawner.enabled = !spawner.enabled;
            button.text.content = info + spawner.enabled;
        };
        scene.ui.add(button);

        StringGameObject str = new StringGameObject("World Text: Flight");
        str.setOrigin(0.5, 0.5);
        str.setFont(new Font("黑体", Font.BOLD, 20));
        scene.add(str);
    }
}