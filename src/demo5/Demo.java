package demo5;

import components.AutoGun;
import components.MousePosition;
import components.RotateTowardsMouse;
import components.ShipController;
import java2d.game.Game;
import java2d.game.GameObject;
import java2d.game.GameScene;
import java2d.game.StringGameObject;
import models.Ship;

import java.awt.*;
import java.awt.geom.Point2D;

public class Demo {

    public static void main(String[] args) {
        Game.debugEnabled = true;

        Game game = new Game("Free Shooter");
        GameScene scene = game.getScene();
        Dimension renderSize = game.getRenderSize();

        // Create ship
        Ship ship = new Ship("player");
        ship.transform.setPosition(0, 250);
        ShipController shipController = ship.getComponent(ShipController.class);
        shipController.horizontalEnabled = false;
        shipController.verticalEnabled = false;
        shipController.shootContinues = false;

        RotateTowardsMouse rotator = new RotateTowardsMouse();
        ship.addComponent(rotator);

        AutoGun autoGun = ship.getComponent(AutoGun.class);
        autoGun.mouseDistanceLimited = true;

        scene.add(ship);

        GameObject mousePointer = new GameObject();
        mousePointer.addComponent(new MousePosition());
        scene.add(mousePointer);

        StringGameObject scores = new StringGameObject("Hello World!!!!", Color.red);
        scores.transform.setPosition(0, -renderSize.height * 0.5);
        scores.setOrigin(0.5, 0);
        scene.add(scores);
    }
}
