package demo2;

import java2d.game.Game;
import java2d.game.GameScene;
import java2d.game.SortGroup;
import java2d.game.SpriteGameObject;

public class Demo {

    public static void main(String[] args) {
        Game.debugEnabled = true;

        Game game = new Game();
        GameScene scene = game.getScene();
        createPlayer(scene);
    }

    private static void createPlayer(GameScene scene) {
        SpriteGameObject player = new SpriteGameObject("player", "assets/sprites/superman.png");
        player.setRenderOrder(1);

        SpriteGameObject weapon = new SpriteGameObject("weapon",
                "assets/sprites/sword.png", 0, 0.5);
        weapon.setRenderOrder(2);
        weapon.transform.setLocalPosition(50, 60);

        player.add(weapon);

        SortGroup sortGroup = new SortGroup();
        sortGroup.setRenderOrder(1);
        player.addComponent(sortGroup);

        Player playerScript = new Player();
        playerScript.weapon = weapon;
        player.addComponent(playerScript);

        scene.add(player);
    }
}
