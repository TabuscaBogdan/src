package shapes;

import java.awt.*;

/**
 * Created by Octavian on 4/2/2017.
 */
@SuppressWarnings("unused")
interface IToolbar {
    int getLineWidth();

    int getNumberOfShapes();

    int getNumberOfSides();

    int getTransparency();

    Color getColor();
}
