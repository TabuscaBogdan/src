package shapes;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Bogdan on 01.04.2017.
 */

public class Canvas extends JPanel{

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
    }

    public Canvas() {
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3, false));
    }
}
