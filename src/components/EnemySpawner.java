package components;

import java2d.game.Game;
import java2d.game.GameComponent;
import java2d.game.GameScene;
import java2d.game.Time;
import models.Enemy;
import utils.Global;

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
        game = (Game) Global.map.get("game");
        scene = game.getScene();
    }

    public void spawn() {
        Enemy enemy = new Enemy();
        double padding = 100d;
        double randomX = random.nextDouble(padding, game.getRenderSize().getWidth() - padding);
        enemy.transform.setPosition(randomX, -padding);
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
