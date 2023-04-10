package demo8;

import java2d.game.Game;
import java2d.game.GameScene;
import java2d.game.ui.Button;

public class Demo {

    public static void main(String[] args) {
        Game game = new Game();
        GameScene scene = game.getScene();

        Button btnTest = new Button();
        btnTest.text.content = "Test";
        btnTest.setSize(100, 50);
        btnTest.transform.setPosition(100, 100);
        btnTest.clickedEvent = (btn, mouseBtn) -> {
            System.out.println("Test~");
        };

        scene.ui.add(btnTest);
    }
}
