package models;

import java2d.game.FrameAnimation;
import java2d.game.Game;
import java2d.game.Sprite;
import java2d.game.SpriteGameObject;
import utils.Global;

import java.awt.geom.Point2D;

public class Explosion extends SpriteGameObject implements FrameAnimation.CompletedEvent {

    private static final String basePath = "assets.sprites/explosion/";

    public Explosion() {
        this(basePath + "explosion-1.png");
    }

    public Explosion(String spritePath) {
        super(spritePath, 0.5, 0.5);
        transform.setLocalScale(2, 2);
        FrameAnimation animation = addAnimationComponent();
        animation.play();
    }

    private FrameAnimation addAnimationComponent() {
        int length = 5;
        FrameAnimation animation = new FrameAnimation();
        animation.frames = new Sprite[length];
        animation.frames[0] = getSprite();
        animation.loop = false;
        animation.completed = this;

        for (int i = 1; i < length; i++)
            animation.frames[i] = Sprite.load(basePath + "explosion-" + (i + 1) + ".png");

        addComponent(animation);
        return animation;
    }

    @Override
    public void completed() {
        destroy();
    }

    public static void explode(Point2D position) {
        Explosion explosion = new Explosion();
        explosion.transform.setPosition(position);
        Game game = (Game) Global.map.get("game");
        game.getScene().add(explosion);
    }
}
