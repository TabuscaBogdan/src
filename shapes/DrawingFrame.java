package shapes;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Octavian on 01.04.2017.
 */

public class DrawingFrame extends JFrame{

    public DrawingFrame(){
        super("Tavbusca Paint!");
        createWindow();
    }

    private Point getGuiStartLocation(){
        int locationY = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
        locationY -= this.getSize().getHeight() / 2;
        int locationX = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
        locationX -= this.getSize().getWidth() / 2;
        return new Point(locationX, locationY);
    }

    private void createWindow()
    {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        JPanel toolbar = new Toolbar();
        this.add(toolbar, BorderLayout.NORTH);

        JPanel controlPanel = new ControlPanel();
        this.add(controlPanel, BorderLayout.SOUTH);

        this.pack();
        this.setSize(800, 600);
        this.setLocation(getGuiStartLocation());
        this.setVisible(true);
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DrawingFrame();
            }
        });

    }

}
