package shapes;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Created by Bogdan on 01.04.2017.
 */

public class ControlPanel extends JPanel {
    JLabel options;
    JButton save;
    JButton draw;
    JButton load;
    JButton clear;


    public void saveComponentAsJPEG(Component myComponent, String filename) {
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
            System.out.println(e);
        }
    }

    public ControlPanel() {
        options = new JLabel("Select Task:");
        save = new JButton("Save");
        draw = new JButton("Draw");
        load = new JButton("Load");
        clear = new JButton("Clear");

        //==================================
        //         Listeners:
        //==================================

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "The canvas has been cleaned,Not rly...");

            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser openFile = new JFileChooser();
                int result = openFile.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = openFile.getSelectedFile();
                    //pathField.setText(selectedFile.getAbsolutePath());
                    //do something with the file, like show picture
                }
            }
        });

        draw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get input from toolbar
                // apeleaza drawn in canvas
            }
        });

        save.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser saveFile=new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JPG & GIF Images", "jpg", "png");
                int result = saveFile.showSaveDialog(null);
                if(result== JFileChooser.APPROVE_OPTION) {
                    File fileToSave = saveFile.getSelectedFile();
                }
            }
        }));


        //==================================
        this.add(options);
        this.add(draw);
        this.add(save);
        this.add(load);
        this.add(clear);
        //==================================
        this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2, false));
        //==================================

    }
}