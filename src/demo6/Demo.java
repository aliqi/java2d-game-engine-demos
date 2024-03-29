package demo6;

import java2d.game.Game;
import java2d.game.GameScene;
import models.CirclePainter;
import models.RotateGameObject;

import java.awt.*;

public class Demo {

    public static void main(String[] args) {
        Game game = new Game();
        GameScene scene = game.getScene();

        RotateGameObject center = new RotateGameObject(1);
        scene.add(center);

        // add a painter
        CirclePainter p1 = new CirclePainter();
        p1.transform.setLocalPosition(0, -100);
        center.add(p1);
    }
}
