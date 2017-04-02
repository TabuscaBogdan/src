package shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

/**
 * Created by Bogdan on 01.04.2017.
 */

public class ControlPanel extends JPanel {
    JLabel options;
    JButton save;
    JButton draw;
    JButton load;
    JButton clear;


    JPanel canvas;

    public ControlPanel() {
        options = new JLabel("Select Task:");
        save = new JButton("Save");
        draw = new JButton("Draw");
        load = new JButton("Load");
        clear = new JButton("Clear");

        //=========================(pt usurinta preluarii si pasarii datelor, (modifiable)
        JPanel canvas = new Canvas();
        this.setSize(800, 600);
        this.add(canvas);

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
                JOptionPane.showMessageDialog(null, "test2.0");
            }
        }));


        //==================================
        this.add(options);
        this.add(draw);
        this.add(save);
        this.add(load);
        this.add(clear);
        //==================================
        this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2, true));
        //==================================

    }
}