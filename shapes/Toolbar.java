package shapes;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.NumericShaper;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

/**
 * Created by Octavian on 02.04.2017.
 */

public class Toolbar extends JPanel{
    private JButton colorSetter;
    private JComboBox nbSidesSetter;
    private Color selectedColor = Color.black;
    private JLabel labelFromComboBox;
    private JLabel labelFromTextField;
    private JTextField nbShapesSetter;
    private JButton drawButton;
    private Canvas canvas;
    private int lineWidth = 1;

    private void drawShapes(){
        int nbOfShapes;
        try {
            nbOfShapes = Integer.valueOf(nbShapesSetter.getText());
        }catch (java.lang.NumberFormatException e){
            nbOfShapes = 1;
        }
        int nbOfSides = nbSidesSetter.getSelectedIndex() + 3; // primul element e 3. deci 3 + 0 = 3

        Graphics2D g = (Graphics2D) canvas.getGraphics();
        g.setColor(selectedColor);
        g.setPaintMode();
        g.setStroke(new BasicStroke(lineWidth));
        Random randomer = new Random();

        for(int i = 0; i < nbOfShapes; ++i){
            int x = randomer.nextInt(canvas.getWidth());
            int y = randomer.nextInt(canvas.getHeight());
            g.drawPolygon(new RegularPolygon(x, y, 50, nbOfSides));
        }
    }

    public Color getSelectedColer(){
        return selectedColor;
    }

    private void showColorChooser(){
        JFrame colorChooserFrame = new JFrame();
        colorChooserFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        colorChooserFrame.setLayout(new BorderLayout());

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
        colorChooserFrame.add(colorChooser, BorderLayout.NORTH);

        JLabel strokeInfo = new JLabel("   Choose line width: ");
        colorChooserFrame.add(strokeInfo);

        JSlider slider = new JSlider(1, 50);
        slider.setValue(lineWidth);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lineWidth = slider.getValue();
            }
        });
        colorChooserFrame.add(slider, BorderLayout.SOUTH);

        colorChooserFrame.pack();

        int locationY = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2;
        locationY -= colorChooserFrame.getSize().getHeight() / 2;
        int locationX = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2;
        locationX -= colorChooserFrame.getSize().getWidth() / 2;

        colorChooserFrame.setLocation(new Point(locationX, locationY));
        colorChooserFrame.setVisible(true);
    }

    public void setCanvas(Canvas canvas){
        this.canvas = canvas;
    }

    public Toolbar(){
        this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2, false));
        this.setLayout(null);
        this.setPreferredSize(new Dimension(800, 36));

        labelFromComboBox = new JLabel("Number of sides:");
        labelFromComboBox.setBounds(15, 5, 100, 26);
        this.add(labelFromComboBox);

        nbSidesSetter = new JComboBox();
        for(int i = 3; i <= 16; ++i)
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
