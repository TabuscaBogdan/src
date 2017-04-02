package shapes; /**
 * Created by Bogdan on 01.04.2017.
 */
import javafx.scene.canvas.*;
import javafx.scene.canvas.Canvas;

import javax.swing.*;
import java.awt.*;

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
        JPanel toolbar = new Toolbar();
        this.add(toolbar);
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
