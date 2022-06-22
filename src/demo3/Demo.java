package demo3;

import java2d.game.*;
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
        game.start();

        GameScene scene = new GameScene();
        game.load(scene);

        Dimension renderSize = game.getFrame().getRenderSize();

        Transform centerDot = createDot(scene);
        centerDot.setPosition(renderSize.getWidth() * 0.5, renderSize.getHeight() * 0.5);

        Transform player = createPlayer(renderSize, scene);
        Transform dot = createDot(scene);

        dot.setPosition(player.getPosition());
    }

    private static Transform createDot(GameScene scene) {
        GameObject dot = new GameObject("dot");

        SpriteRender spriteRender = new SpriteRender();
        Sprite sprite = Sprite.load("assets.sprites/dot.png");
        spriteRender.sprite = sprite;
        spriteRender.setOrder(99);
        dot.addComponent(spriteRender);

        scene.add(dot);

        Transform transform = dot.transform;
        transform.setOrigin(sprite.getWidth() * 0.5, sprite.getHeight() * 0.5);

        return transform;
    }

    private static Transform createPlayer(Dimension renderSize, GameScene scene) {
        GameObject player = new GameObject("player");

        SpriteRender spriteRender = new SpriteRender();
        Sprite sprite = Sprite.load("assets.sprites/spacecraft.png");
        spriteRender.sprite = sprite;
        spriteRender.setOrder(1);
        player.addComponent(spriteRender);

        Transform transform = player.transform;

        transform.setOrigin(sprite.getWidth() * 0.5, sprite.getHeight() * 0.5);

        System.out.println(renderSize.getWidth() * 0.5);
        System.out.println(renderSize.getHeight() * 0.5);
        transform.setPosition(renderSize.getWidth() * 0.5, renderSize.getHeight() * 0.5);
        Point2D position = transform.getPosition();
        System.out.println(position);

        transform.setLocalScale(2, 2);

        scene.add(player);

        player.addComponent(new PlayerInput(scene));
//        player.addComponent(new Rotator());

        return transform;
    }
}
