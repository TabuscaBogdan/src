package shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Octavian on 01.04.2017.
 */

public class Canvas extends JComponent {

    private Image image;
    private Graphics2D g2;
    private Toolbar toolbar;

    Canvas() {
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX, mouseY;
                mouseX = e.getX();
                mouseY = e.getY();
                Color chosenColor = toolbar.getColor();
                g2.setColor(new Color(chosenColor.getRed(), chosenColor.getGreen(), chosenColor.getBlue(),
                        toolbar.getTransparency()));
                g2.setStroke(new BasicStroke(toolbar.getLineWidth()));
                g2.drawPolygon(new RegularPolygon(mouseX, mouseY, toolbar.getNumberOfSides()));
            }
        });
    }

    void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public Graphics2D getGraphics() {
        return g2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image == null) {
            System.out.println("[Function Canvas] Paint component called!");
            image = createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
        repaint();
    }

    public void clear() {
        g2.setPaint(new Color(255, 255, 255, 255));
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(new Color(0, 0, 0, toolbar.getTransparency()));
    }

}

