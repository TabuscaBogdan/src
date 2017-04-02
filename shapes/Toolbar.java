package shapes;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.NumericShaper;

/**
 * Created by Octavian on 02.04.2017.
 */

public class Toolbar extends JPanel{
    private JButton colorSetter;
    private JComboBox nbSidesSetter;
    private Color selectedColor;
    private JLabel labelFromComboBox;
    private JLabel labelFromTextField;
    private JTextField nbShapesSetter;
    private JButton drawButton;

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
                colorSetter.setBackground(selectedColor);
                colorSetter.setForeground(new Color(
                        255 - selectedColor.getRed(),
                        255 - selectedColor.getGreen(),
                        255 - selectedColor.getBlue()
                ));
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

    private void drawShapes(){
        int nbOfShapes = Integer.valueOf(nbShapesSetter.getText());
        int nbOfSides = nbSidesSetter.getSelectedIndex() + 3; // primul element e 3. deci 3 + 0 = 3
        selectedColor.toString();
    }

    public Toolbar(){
        this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2, false));
        this.setLayout(null);
        this.setPreferredSize(new Dimension(800, 36));

        labelFromComboBox = new JLabel("Number of sides:");
        labelFromComboBox.setBounds(15, 5, 100, 26);
        this.add(labelFromComboBox);

        nbSidesSetter = new JComboBox();
        for(int i = 3; i <= 16; i++)
            nbSidesSetter.addItem(Integer.toString(i));
        nbSidesSetter.setBounds(125, 5, 50, 26);
        this.add(nbSidesSetter);

        labelFromTextField = new JLabel("Number of shapes:");
        labelFromTextField.setBounds(240, 5, 110, 26);
        this.add(labelFromTextField);

        nbShapesSetter = new JTextField();
        nbShapesSetter.setBounds(355,5, 45, 26);
        this.add(nbShapesSetter);

        colorSetter = new JButton("Choose your color");
        colorSetter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showColorChooser();
            }
        });
        colorSetter.setBounds(460, 5, 140,26);
        this.add(colorSetter);

        drawButton = new JButton("Draw Shapes");
        drawButton.setBounds(625, 5, 140, 26);
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawShapes();
            }
        });
        this.add(drawButton);
    }
}
