package demo7;

import components.Follower;
import java2d.game.Camera;
import java2d.game.Game;
import java2d.game.GameScene;
import java2d.game.SpriteGameObject;
import models.Ship;

import java.awt.*;

/**
 * Author: ZhaoYan
 * Created: 2023/3/28 21:37
 */
public class Demo {

    public static void main(String[] args) {
        Game game = new Game();
        GameScene scene = game.getScene();

        SpriteGameObject background = new SpriteGameObject("assets/sprites/background.png");
        scene.add(background);

        Ship ship = new Ship("ship");
        Follower follower = new Follower(ship);
        Camera camera = scene.getCamera();

        camera.getGameObject().addComponent(follower);

        scene.add(ship);
    }
}
