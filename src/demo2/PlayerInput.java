package demo2;

import java2d.game.*;

import java.awt.event.MouseEvent;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/6 17:40
 */
public class PlayerInput extends GameComponent implements GameMouseEvent {

    public double speed = 5.0;

    @Override
    protected void awake() {
        super.awake();
        Inputs.addMouseEventListener(this);
    }

    @Override
    protected void destroy() {
        super.destroy();
        Inputs.removeMouseEventListener(this);
    }

    @Override
    protected void update() {
        double x = 0, y = 0;

        if (Inputs.getKey('w')) y -= 1;
        if (Inputs.getKey('s')) y += 1;
        if (Inputs.getKey('a')) x -= 1;
        if (Inputs.getKey('d')) x += 1;

        GameObject gameObject = getGameObject();
        Transform transform = gameObject.transform;

        double t = speed * Time.deltaTime * 100;
        transform.translate(t * x, t * y);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouse pressed");
    }
}