package utils;

import java.awt.geom.Point2D;

/**
 * Author:     Zhao Yan
 * DateTime:   2022/6/7 17:27
 */
public class Directions {

    private Directions() {
    }

    public static double toRotation(double x, double y) {
        return Math.toDegrees(Math.atan2(y, x));
    }

    public static double toRotation(Point2D direction) {
        return toRotation(direction.getX(), direction.getY());
    }

    public static Point2D multiple(Point2D p, double v) {
        return new Point2D.Double(p.getX() * v, p.getY() * v);
    }
}
