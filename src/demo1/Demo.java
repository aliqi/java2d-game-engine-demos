package demo1;

import java2d.game.Canvas;
import java2d.game.*;
import java2d.game.ui.Panel;
import java2d.game.ui.Picture;
import java2d.game.ui.Text;

import java.awt.*;
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

        SpriteGameObject inner = new SpriteGameObject("square",
                "assets/sprites/dot.png", 0.5, 0.5);
        inner.transform.setLocalScale(4, 4);

        square.add(inner);

        // Create string
        StringGameObject text = new StringGameObject("你好", Color.white);
        text.getStringRender().boundsVisible = true;
        game.getScene().add(text);

        // Create string with font
        StringGameObject fontText = new StringGameObject("你好", Color.green);
        Font font = new Font("楷体", Font.PLAIN, 30);
        fontText.setFont(font);
        fontText.getStringRender().boundsVisible = true;
        fontText.transform.setPosition(0, 50);
        game.getScene().add(fontText);

        Canvas ui = game.getScene().ui;
        var panel = new Panel();
        panel.transform.setPosition(500, 150);
        panel.origin.setLocation(0.5, 0.5);
        panel.setSize(new Dimension(200, 200));
        ui.add(panel);

        var innerPanel = new Panel();
        innerPanel.transform.setPosition(50, 50);
        innerPanel.transform.setLocalRotation(30);
        innerPanel.backgroundColor = Color.blue;
        innerPanel.origin.setLocation(0.2, 0.2);
        innerPanel.setSize(new Dimension(150, 150));
        panel.add(innerPanel);

        var pointPanel = new Panel();
        pointPanel.setSize((int) (innerPanel.getWidth() * innerPanel.origin.getX()),
                (int) (innerPanel.getHeight() * innerPanel.origin.getY()));
        pointPanel.backgroundColor = Color.red;
        innerPanel.add(pointPanel);

        Text text1 = new Text();
        text1.content = "Cool";
        text1.font = font;
        text1.boundsVisible = true;
        text1.origin.setLocation(0.5, 0.5);
        text1.transform.setPosition(innerPanel.getWidth() * 0.5,
                innerPanel.getHeight() * 0.5);
        innerPanel.add(text1);

        Picture picture = new Picture(Images.load("assets/sprites/observer.png"));
        picture.setSize(150, 100);
        picture.backgroundColor = Color.yellow;
        picture.transform.setPosition(200, 400);
        picture.transform.setLocalRotation(90);
        picture.borderColor = Color.green;
        game.getScene().ui.add(picture);

        SpriteGameObject ship = new SpriteGameObject("assets/sprites/observer.png", 0.5, 0.5);
        ship.transform.setPosition(200, 200);
        ship.transform.setLocalRotation(45);
        game.getScene().add(ship);
    }
}
