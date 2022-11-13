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
        Game game = new Game("Hello");

        Dimension renderSize = game.getFrame().getRenderSize();
        System.out.println("Render size: " + renderSize);

        // Create a square
        SpriteGameObject square = new SpriteGameObject("square",
                "classpath:assets/sprites/square.png", 0.5, 0.5);

        square.transform.setPosition(
                renderSize.getWidth() * 0.5,
                renderSize.getHeight() * 0.5);

        game.getScene().add(square);
    }
}
