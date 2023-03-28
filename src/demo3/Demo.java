package demo3;

import components.Rotator;
import java2d.game.Game;
import java2d.game.GameScene;
import java2d.game.Transform;
import models.Dot;
import models.Ship;

import java.awt.geom.Point2D;

public class Demo {

    public static void main(String[] args) {
        Game.debugEnabled = true;

        Game game = new Game();
        GameScene scene = game.getScene();

        createDot(scene, 0, 0);

        Transform player = createPlayer(scene);
        Point2D playerPosition = player.getPosition();
        createDot(scene, playerPosition.getX(), playerPosition.getY() + 100);
    }

    private static Transform createDot(GameScene scene, double x, double y) {
        Dot dot = Dot.create(x, y);
        scene.add(dot);
        return dot.transform;
    }

    private static Transform createPlayer(GameScene scene) {
        Ship player = new Ship("player", 100,
                "assets/sprites/spacecraft1.png", 0.5, 0.5, 1);

        Transform transform = player.transform;
        scene.add(player);

        player.addComponent(new Rotator());

        return transform;
    }
}
