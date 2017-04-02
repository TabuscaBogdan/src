package shapes;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Bogdan on 01.04.2017.
 */

public class Canvas extends JPanel{
    JLabel title;

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        RegularPolygon p=new RegularPolygon(8,8,8,5);
        g.drawPolygon(p);
    }

    public Canvas() {

        title = new JLabel("Sketch");
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 3, false));
        this.setSize(800, 400);

        this.add(title);
    }
}
