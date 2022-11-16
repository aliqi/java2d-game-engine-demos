package demo3;

import components.Rotator;
import java2d.game.Game;
import java2d.game.GameScene;
import java2d.game.Transform;
import models.Dot;
import models.Ship;
import utils.Global;

import java.awt.*;
import java.awt.geom.Point2D;

public class Demo {

    public static void main(String[] args) {
        Game.debugEnabled = true;

        Game game = new Game();
        GameScene scene = game.getScene();
        Dimension renderSize = game.getFrame().getRenderSize();

        System.out.println(renderSize);

        Global.map.put("game", game);

        createDot(scene, renderSize.getWidth() * 0.5, renderSize.getHeight() * 0.5);

        Transform player = createPlayer(renderSize, scene);
        Point2D playerPosition = player.getPosition();
        createDot(scene, playerPosition.getX(), playerPosition.getY());
    }

    private static Transform createDot(GameScene scene, double x, double y) {
        Dot dot = Dot.create(x, y);
        scene.add(dot);
        return dot.transform;
    }

    private static Transform createPlayer(Dimension renderSize, GameScene scene) {
        Ship player = new Ship("player",
                "assets.sprites/spacecraft1.png", 0.5, 0.5, 1);

        Transform transform = player.transform;
        transform.setPosition(renderSize.getWidth() * 0.5, renderSize.getHeight() * 0.5);
        scene.add(player);

        player.addComponent(new Rotator());

        return transform;
    }
}
