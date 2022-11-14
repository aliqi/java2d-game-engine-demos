package components;

import java2d.game.SpriteGameObject;

import java.awt.geom.Point2D;

public class Enemy extends SpriteGameObject implements HP.ValueChanged {

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

        addHpComponent();
    }

    private void addHpComponent() {
        HP hp = new HP();
        hp.setMaxValue(100);
        hp.reset();
        hp.valueChanged = this;

        addComponent(hp);
    }

    @Override
    public void changed(int newValue, int oldValue) {
        if (newValue == 0) {
            // Die: spawn particles and destroy game object

            destroy();
        }
    }
}
