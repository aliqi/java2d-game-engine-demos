package demo3;

import java2d.game.*;
import utils.Global;
import utils.Rotator;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/7 16:03
 */
public class Demo {

    public static void main(String[] args) {
        Game game = new Game();
        GameScene scene = game.getScene();
        Dimension renderSize = game.getFrame().getRenderSize();

        Global.map.put("game", game);

        Transform centerDot = createDot(scene);
        centerDot.setPosition(renderSize.getWidth() * 0.5, renderSize.getHeight() * 0.5);

        Transform player = createPlayer(renderSize, scene);
        Transform dot = createDot(scene);

        dot.setPosition(player.getPosition());
    }

    private static Transform createDot(GameScene scene) {
        SpriteGameObject dot = new SpriteGameObject("dot",
                "assets.sprites/dot.png", 0.5, 0.5);
        dot.setOrder(99);

        scene.add(dot);
        return dot.transform;
    }

    private static Transform createPlayer(Dimension renderSize, GameScene scene) {
        SpriteGameObject player = new SpriteGameObject("player",
                "assets.sprites/spacecraft.png", 0.5, 0.5);
        player.setOrder(1);

        Transform transform = player.transform;
        transform.setPosition(renderSize.getWidth() * 0.5, renderSize.getHeight() * 0.5);
        transform.setLocalScale(2, 2);

        scene.add(player);

        player.addComponent(new PlayerInput(scene));
        player.addComponent(new AutoGun());
        player.addComponent(new Rotator());

        return transform;
    }
}
