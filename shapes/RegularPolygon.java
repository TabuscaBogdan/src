package shapes;

import java.awt.*;

/**
<<<<<<< HEAD
 * Created by iilie on 4/2/2017.
 */
public class RegularPolygon extends Polygon {

=======
 * Created by Bogdan on 01.04.2017.
 */
public class RegularPolygon extends Polygon {
>>>>>>> 7f0c1cd766314e0d353ea259fc2762a2c7fc9e58
    public RegularPolygon(int x0, int y0, int radius, int sides) {
        double alpha = 2 * Math.PI / sides;
        for (int i = 0; i < sides; i++) {
            double x = x0 + radius * Math.cos(alpha * i);
            double y = y0 + radius * Math.sin(alpha * i);
            this.addPoint((int) x, (int) y);
        }
    }
<<<<<<< HEAD
}
=======

}
>>>>>>> 7f0c1cd766314e0d353ea259fc2762a2c7fc9e58
