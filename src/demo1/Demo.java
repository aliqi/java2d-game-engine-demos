package demo1;

import java2d.game.*;

import java.awt.*;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/6 17:03
 */
public class Demo {

    public static void main(String[] args) {

        // Create game and start it.
        Game game = new Game();
        game.getFrame().setTitle("Hello");
        game.start();

        Dimension renderSize = game.getFrame().getRenderSize();
        System.out.println("Render size: " + renderSize);

        // Create scene
        GameScene scene = new GameScene();

        // Create a square
        createSquare(renderSize, scene);

        game.load(scene);
    }

    private static void createSquare(Dimension renderSize, GameScene scene) {
        GameObject square = new GameObject("square");

        // Create SpriteRender
        SpriteRender spriteRender = new SpriteRender();
        Sprite sprite = Sprite.load("classpath:assets/sprites/square.png");
        spriteRender.sprite = sprite;

        square.addComponent(spriteRender);
        square.transform.setLocalPosition(
                (renderSize.getWidth() - sprite.getWidth()) * 0.5,
                (renderSize.getHeight() - sprite.getHeight()) * 0.5);

        scene.add(square);
    }
}
