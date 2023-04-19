package demo8;

import components.DestinationTranslator;
import java2d.game.Game;
import java2d.game.GameScene;
import java2d.game.SpriteGameObject;
import java2d.game.ui.Button;
import models.Ship;

public class Demo {

    public static void main(String[] args) {
        Game game = new Game();
        GameScene scene = game.getScene();

        SpriteGameObject ship = new SpriteGameObject("assets/sprites/spacecraft1.png");
        DestinationTranslator translator = new DestinationTranslator();
        translator.rotationDelta = 90;
        ship.addComponent(translator);
        scene.add(ship);

        Button btnTest = new Button();
        btnTest.text.content = "Test";
        btnTest.setSize(100, 50);
        btnTest.transform.setPosition(100, 100);
        btnTest.clickedEvent = (btn, mouseBtn) -> {
            translator.destination.setLocation(100, 100);
            translator.reachedEvent = t -> System.out.println("Reached");
            translator.begin();
        };

        scene.ui.add(btnTest);
    }
}
