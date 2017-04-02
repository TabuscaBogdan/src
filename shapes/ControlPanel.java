package shapes;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.nio.*;
/**
 * Created by Bogdan on 01.04.2017.
 */

class ControlPanel extends JPanel {
    private Canvas canvas;

    void setCanvas(Canvas canvas){
        this.canvas = canvas;
    }

    Canvas getCanvas() {
        return canvas;
    }

    private void saveImage(Component myComponent, File filename) {
        Dimension size = myComponent.getSize();
        BufferedImage myImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = myImage.createGraphics();
        myComponent.paint(g2);
        try {
            OutputStream out = new FileOutputStream(filename);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(myImage);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ControlPanel() {
        JLabel options = new JLabel("Select Task:");
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");
        JButton clear = new JButton("Clear");

        //==================================
        //         Listeners:
        //==================================

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.clear();
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser openFile = new JFileChooser();
                int result = openFile.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = openFile.getSelectedFile();
                    Graphics2D g2 = canvas.getGraphics();
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(selectedFile);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    g2.drawImage(image, 0, 0, canvas);
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser saveFile=new JFileChooser();
                saveFile.setSelectedFile(new File("Shapes.jpg"));

                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "PNG image", "png");
                saveFile.setFileFilter(filter);

                int result = saveFile.showSaveDialog(null);
                if(result== JFileChooser.APPROVE_OPTION) {
                    File fileToSave = saveFile.getSelectedFile();
                    saveImage(canvas, fileToSave);
                    System.out.println("[Debug][Control Panel] Saved image");
                }
            }
        });


        //==================================
        this.add(options);
        this.add(save);
        this.add(load);
        this.add(clear);
        //==================================
        this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2, false));
        //==================================
    }

}