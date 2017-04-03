package shapes;

import java.awt.*;

class RegularPolygon extends Polygon {

    private static final int RADIUS = 50;

    public RegularPolygon(int x0, int y0, int sides) {
        double alpha = 2 * Math.PI / sides;
        for (int i = 0; i < sides; ++i) {
            double x = x0 + RADIUS * Math.cos(alpha * i);
            double y = y0 + RADIUS * Math.sin(alpha * i);
            this.addPoint((int) x, (int) y);
        }
    }
}

