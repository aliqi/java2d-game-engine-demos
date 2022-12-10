package demo5;

import components.MousePosition;
import components.RotateTowardsMouse;
import components.ShipController;
import java2d.game.*;
import models.Ship;
import utils.Global;

import java.awt.*;
import java.awt.geom.Point2D;

public class Demo {

    public static void main(String[] args) {
        Game.debugEnabled = true;

        Game game = new Game("Free Shooter");
        GameScene scene = game.getScene();
        Dimension renderSize = game.getRenderSize();
        System.out.println("Render size: " + renderSize);

        // App-level singleton
        Global.map.put("game", game);

        // Create ship
        Ship ship = new Ship("player");
        ship.transform.setPosition(new Point2D.Double(renderSize.getWidth() * 0.5, renderSize.getHeight() - 50));
        ship.getComponent(ShipController.class).enabled = false;

        RotateTowardsMouse rotator = new RotateTowardsMouse();
//        rotator.visible = false;
        ship.addComponent(rotator);

        StringRender render = new StringRender();
        render.setRenderOrder(9);
        ship.addComponent(render);

        scene.add(ship);

        GameObject mousePointer = new GameObject();
        mousePointer.addComponent(new MousePosition());
        scene.add(mousePointer);

        StringGameObject scores = new StringGameObject("Hello World!!!!", Color.red);
        scores.transform.setPosition(renderSize.getWidth() * 0.5, 0);
        scores.setOrigin(0.5, 0);
        scene.add(scores);
    }
}
