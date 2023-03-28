package components;

import java2d.game.Game;
import java2d.game.GameComponent;
import java2d.game.GameScene;
import java2d.game.Time;
import models.Enemy;

import java.awt.*;
import java.util.Random;

public class EnemySpawner extends GameComponent {

    public double minSpawnInterval = 0.3f;

    public double maxSpawnInterval = 2f;

    private final Random random = new Random();

    private Game game;
    private GameScene scene;

    private double spawnInterval;

    private double timer;

    @Override
    protected void awake() {
        super.awake();
        scene = getScene();
        game = scene.getGame();
    }

    public void spawn() {
        Enemy enemy = new Enemy();
        double padding = 100d;
        Dimension renderSize = game.getRenderSize();
        double halfWidth = renderSize.width * 0.5;
        double randomX = random.nextDouble(-halfWidth + padding, halfWidth - padding);
        enemy.transform.setPosition(randomX, -renderSize.height * 0.5 - padding);
        enemy.addTranslatorComponent();
        scene.add(enemy);
    }

    @Override
    protected void update() {
        timer += Time.deltaTime;
        if (timer >= spawnInterval) {
            spawnInterval = random.nextDouble(minSpawnInterval, maxSpawnInterval);
            timer = 0;
            spawn();
        }
    }
}
