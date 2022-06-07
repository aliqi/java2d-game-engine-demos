package demo2;

import java2d.game.*;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/6 17:36
 */
public class Demo {

    public static void main(String[] args) {
        Game.debugEnabled = true;

        Game game = new Game();
        game.start();

        GameScene scene = new GameScene();

        createCamera(scene);
        createPlayer(scene);

        game.load(scene);
    }

    private static void createPlayer(GameScene scene) {
        GameObject player = new GameObject("player");
        SpriteRender spriteRender = new SpriteRender();
        spriteRender.sprite = Sprite.load("assets.sprites/superman.png");
        spriteRender.setOrder(1);
        player.addComponent(spriteRender);

        Player playerScript = new Player();
        player.addComponent(playerScript);

        GameObject weapon = new GameObject("weapon");
        spriteRender = new SpriteRender();
        spriteRender.sprite = Sprite.load("assets.sprites/sword.png");
        spriteRender.setOrder(2);
        weapon.addComponent(spriteRender);

        weapon.transform.setLocalPosition(50, 60);
        weapon.transform.setOrigin(0, 6);

        player.add(weapon);

        playerScript.weapon = weapon;

        SortGroup sortGroup = new SortGroup();
        sortGroup.setOrder(1);
        player.addComponent(sortGroup);

        scene.add(player);

        PlayerInput input = new PlayerInput();
        player.addComponent(input);
    }

    private static void createCamera(GameScene scene) {
        GameObject cam = new GameObject("camera");
        Camera camera = new Camera();

        scene.add(cam);
        cam.addComponent(camera);

        // Set scene camera
        scene.setCamera(camera);
    }
}
