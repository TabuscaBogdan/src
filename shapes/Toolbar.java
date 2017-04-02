package shapes;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Octavian on 02.04.2017.
 */

public class Toolbar extends JPanel{
    private JButton colorSetter;
    private Color selectedColor;

    public Color getSelectedColer(){
        return selectedColor;
    }

    private void showColorChooser(){
        JFrame colorChooserFrame = new JFrame();
        colorChooserFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        colorChooserFrame.setLayout(new FlowLayout());

        JColorChooser colorChooser = new JColorChooser();
        colorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                selectedColor = colorChooser.getColor();
                System.out.println("Ai ales culoarea: " + selectedColor.toString());
            }
        });

        colorChooserFrame.add(colorChooser);

        colorChooserFrame.pack();

        int locationY = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
        locationY -= colorChooserFrame.getSize().getHeight() / 2;
        int locationX = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
        locationX -= colorChooserFrame.getSize().getWidth() / 2;

        colorChooserFrame.setLocation(new Point(locationX, locationY));
        colorChooserFrame.setVisible(true);
    }

    public Toolbar(){
        colorSetter = new JButton("Choose your color");
        colorSetter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showColorChooser();
            }
        });
        this.add(colorSetter);
    }
}
