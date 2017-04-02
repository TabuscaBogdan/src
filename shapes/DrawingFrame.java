package shapes;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Octavian on 01.04.2017.
 */

public class DrawingFrame extends JFrame{

    DrawingFrame(){
        super("Lab6 Paint!");
        createWindow();
    }

    private void createWindow() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        Canvas canvas = new Canvas();
        this.add(canvas, BorderLayout.CENTER);

        Toolbar toolbar = new Toolbar();
        this.add(toolbar, BorderLayout.NORTH);

        ControlPanel controlPanel = new ControlPanel();
        this.add(controlPanel, BorderLayout.SOUTH);

        this.pack();
        this.setSize(800, 800);
        this.setResizable(false);
        this.setLocation(DrawingFrame.getCenterScreen(this.getSize()));
        this.setVisible(true);

        toolbar.setCanvas(canvas);
        canvas.setToolbar(toolbar);
        controlPanel.setCanvas(canvas);

        System.out.println("[Debug][DrawingFrame] " + canvas);
        System.out.println("[Debug][DrawingFrame] " + toolbar.getCanvas());

        if(canvas.hashCode() == toolbar.getCanvas().hashCode()){
            System.out.println("[Debug][DrawingFrame] Same canvas...");
        }
    }

    static Point getCenterScreen(Dimension dimension){
        int locationY = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
        locationY -= dimension.getHeight() / 2;
        int locationX = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
        locationX -= dimension.getWidth() / 2;
        return new Point(locationX, locationY);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DrawingFrame();
            }
        });
    }
}
