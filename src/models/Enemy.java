package models;

import components.CircleCollider;
import components.DirectionalTranslator;
import components.HP;
import java2d.game.Game;
import java2d.game.SpriteGameObject;

import java.awt.geom.Point2D;
import java.util.Random;

public class Enemy extends SpriteGameObject implements HP.ValueChanged {

    private final Random random = new Random();

    public Enemy() {
        this("enemy");
    }

    public Enemy(String name) {
        this(name, new Point2D.Double());
    }

    public Enemy(String name, Point2D position) {
        this(name, "assets.sprites/observer.png", 0.5, 1, position);
    }

    public Enemy(String name, String spritePath, double ratioX, double ratioY, Point2D position) {
        super(name, spritePath, ratioX, ratioY);
        transform.setPosition(position);

        CircleCollider circleCollider = new CircleCollider();
        circleCollider.radius = 30;
        circleCollider.offset.setLocation(0, -40);
        circleCollider.visible = Game.debugEnabled;

        addComponent(circleCollider);
        addHpComponent();

        tag = "enemy";
    }

    public void addTranslatorComponent() {
        DirectionalTranslator component = getComponent(DirectionalTranslator.class);

        if (component == null) {
            DirectionalTranslator translator = new DirectionalTranslator();
            translator.direction = new Point2D.Double(0, 1);
            translator.speed = 0.1;
            translator.rotateEnabled = false;

            addComponent(translator);
        }
    }

    private void addHpComponent() {
        HP hp = new HP();
        hp.setMaxValue(random.nextInt(5, 101));
        hp.reset();
        hp.valueChanged = this;

        addComponent(hp);
    }

    @Override
    public void changed(int newValue, int oldValue) {
        if (newValue == 0) {
            // Die: spawn particles and destroy game object
            Point2D position = transform.getPosition();
            position.setLocation(position.getX(), position.getY() - 40);
            Explosion.explode(position);
            destroy();
        }
    }
}
