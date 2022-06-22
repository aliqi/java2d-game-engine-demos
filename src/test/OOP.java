package test;

import demo3.PlayerInput;
import java2d.game.*;
import utils.Rotator;

import java.awt.*;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/9 10:14
 */
public class OOP {

    public static void main(String[] args) {
        Game game = new Game();
        game.start();

        GameScene scene = new GameScene();
        game.load(scene);

        Dimension renderSize = game.getFrame().getRenderSize();

        Transform s1 = createSquare(scene);
        s1.setPosition(renderSize.getWidth() * .5, renderSize.getHeight() * .5);
        s1.getGameObject().addComponent(new Rotator());
        s1.getGameObject().addComponent(new PlayerInput(scene));

        Transform s2 = createSquare(scene);
        s2.getGameObject().addComponent(new PlayerInput(scene));
    }

    public static Transform createSquare(GameScene scene) {
        GameObject square = new GameObject();
        Sprite sprite = Sprite.load("classpath:assets/sprites/square.png");

        SpriteRender render = new SpriteRender();
        render.sprite = sprite;

        square.addComponent(render);

        scene.add(square);

        return square.transform;
    }
}
