package models;

import java2d.game.*;
import utils.Global;

import java.awt.geom.Point2D;

public class Explosion extends SpriteGameObject implements FrameAnimation.CompletedEvent {

    private static final String basePath = "assets.sprites/explosion/";

    private static final SpriteFrames MEMORY_FRAMES;

    private static final SpriteFrames DESC_FRAMES;

    static {
        // MEMORY FRAMES
        int length = 5;
        SpriteFrame[] fs = new SpriteFrame[length];

        for (int i = 0; i < length; i++)
            fs[i] = new SpriteFrame(i, Sprite.load(basePath + "explosion-" + (i + 1) + ".png"));

        MEMORY_FRAMES = new SpriteFrames(fs);

        // -----------------------

        // LOADED FRAMES
		DESC_FRAMES = SpriteFrames.load(basePath + "explosion.ani");
    }

    public Explosion() {
        this(basePath + "explosion-1.png");
    }

    public Explosion(String spritePath) {
        super(spritePath, 0.5, 0.5);
        transform.setLocalScale(2, 2);
        FrameAnimation animation = addAnimationComponent();
//        animation.play();
        animation.play(0.1f);
    }

    private FrameAnimation addAnimationComponent() {
        FrameAnimation animation = new FrameAnimation();
        animation.loop = false;
        animation.completed = this;
//        animation.frames = MEMORY_FRAMES;
        animation.frames = DESC_FRAMES;

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
