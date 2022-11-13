package demo1;

import java2d.game.*;

import java.awt.*;
import java.awt.geom.Point2D;

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

        // Create a line
        GameObject line = new GameObject();
        LineRender lineRender = new LineRender();
        lineRender.points.add(new Point2D.Float());
        lineRender.points.add(new Point2D.Float(100f, 0));
        lineRender.color = Color.red;
        lineRender.stroke = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        line.transform.setPosition(100, 100);
        line.transform.setLocalRotation(30);

        line.addComponent(lineRender);
        game.getScene().add(line);

        // Create a square
        SpriteGameObject square = new SpriteGameObject("square",
                "classpath:assets/sprites/square.png", 0.5, 0.5);

        square.transform.setPosition(
                renderSize.getWidth() * 0.5,
                renderSize.getHeight() * 0.5);

        game.getScene().add(square);
    }
}
