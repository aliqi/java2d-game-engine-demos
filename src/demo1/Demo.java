package demo1;

import java2d.game.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws IOException, FontFormatException {

        // Create game and start it.
        Game game = new Game("Hello");

        Dimension renderSize = game.getFrame().getRenderSize();
        System.out.println("Render size: " + renderSize);

        // Create a line
        GameObject line = new GameObject();
        line.transform.setPosition(100, 100);
        line.transform.setLocalRotation(30);

        LineRender lineRender = new LineRender();
        lineRender.space = Space.local;
        lineRender.points.add(new Point2D.Float());
        lineRender.points.add(new Point2D.Float(100f, 0));
        lineRender.color = Color.red;
        lineRender.stroke = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        line.addComponent(lineRender);
        game.getScene().add(line);

        // Create a square
        SpriteGameObject square = new SpriteGameObject("square",
                "classpath:assets/sprites/square.png", 0.5, 0.5);

        square.transform.setPosition(
                renderSize.getWidth() * 0.5,
                renderSize.getHeight() * 0.5);

        game.getScene().add(square);

        // Create string
        StringGameObject text = new StringGameObject("你好", Color.white);
        game.getScene().add(text);

        // Create string with font
        StringGameObject fontText = new StringGameObject("你好", Color.green);
        Font font = new Font("楷体", Font.PLAIN, 30);
        fontText.setFont(font);
        fontText.transform.setPosition(0, 50);
        game.getScene().add(fontText);

        // Add Button
        Button button = new Button("Click");
        button.setLocation(100, 50);
        button.setSize(50, 20);
        button.setBackground(Color.gray);
        button.setForeground(Color.white);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fontText.setText("Random: " + Math.random());
            }
        });

        game.getFrame().add(button);
    }
}
