package shapes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


/**
 * Created by Octavian on 02.04.2017.
 */

public class Toolbar extends JPanel implements IToolbar {
    private JButton colorSetter;
    private JComboBox<String> nbSidesSetter;
    private JTextField nbShapesSetter;
    private JFrame colorChooserFrame;

    private Color selectedColor = Color.black;
    private int lineWidth = 1;
    private int nbOfShapes = 1;
    private int nbOfSides = 3;
    private int transparency = 127;
    private Canvas canvas;

    Toolbar() {
        this.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2, false));
        this.setLayout(null);
        this.setPreferredSize(new Dimension(800, 36));

        initLabelSides();
        initSidesSetter();
        initLabelShapes();
        initShapesSetter();
        initColorSetterButton();
        initDrawButton();
    }

    @Override
    public int getLineWidth() {
        return this.lineWidth;
    }

    @Override
    public int getNumberOfShapes() {
        return nbOfShapes;
    }

    @Override
    public int getNumberOfSides() {
        return nbOfSides;
    }

    @Override
    public int getTransparency() {
        return transparency;
    }

    @Override
    public Color getColor() {
        return selectedColor;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    private void drawShapes() {
        Graphics2D g2 = canvas.getGraphics();
        g2.setColor(new Color(selectedColor.getRed(), selectedColor.getGreen(), selectedColor.getBlue(), transparency));
        g2.setPaintMode();
        g2.setStroke(new BasicStroke(lineWidth));
        Random random = new Random();

        for (int i = 0; i < nbOfShapes; ++i) {
            int x = random.nextInt(canvas.getWidth());
            int y = random.nextInt(canvas.getHeight());
            g2.drawPolygon(new RegularPolygon(x, y, nbOfSides));
        }
    }

    private void initColorChooser() {
        JColorChooser colorChooser = new JColorChooser();
        colorChooser.getSelectionModel().addChangeListener(e -> {
            selectedColor = colorChooser.getColor();
            colorSetter.setBackground(selectedColor);
            colorSetter.setForeground(new Color(
                    255 - selectedColor.getRed(),
                    255 - selectedColor.getGreen(),
                    255 - selectedColor.getBlue()
            ));
        });
        colorChooserFrame.add(colorChooser, BorderLayout.NORTH);
    }

    private void initLineWidthSlider() {
        JSlider slider = new JSlider(1, 50);
        slider.setValue(lineWidth);
        slider.addChangeListener(e -> lineWidth = slider.getValue());

        JLabel strokeInfo = new JLabel("   Line width: ");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(strokeInfo, BorderLayout.NORTH);
        panel.add(slider, BorderLayout.SOUTH);
        colorChooserFrame.add(panel, BorderLayout.CENTER);
    }

    private void initTransparencySlider() {
        JSlider slider = new JSlider(0, 255);
        slider.setValue(transparency);
        slider.addChangeListener(e -> transparency = slider.getValue());

        JLabel transparencyInfo = new JLabel("   Line transparency: ");

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(transparencyInfo, BorderLayout.NORTH);
        panel.add(slider, BorderLayout.SOUTH);
        colorChooserFrame.add(panel, BorderLayout.SOUTH);
    }

    private void initColorChooserFrame() {
        colorChooserFrame = new JFrame();
        colorChooserFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        colorChooserFrame.setLayout(new BorderLayout());

        initColorChooser();
        initLineWidthSlider();
        initTransparencySlider();

        colorChooserFrame.pack();
        colorChooserFrame.setLocation(DrawingFrame.getScreenCenter(colorChooserFrame.getSize()));
        colorChooserFrame.setVisible(true);
    }

    private void initLabelSides() {
        JLabel labelFromComboBox = new JLabel("Number of sides:");
        labelFromComboBox.setBounds(15, 5, 100, 26);
        this.add(labelFromComboBox);
    }

    private void initSidesSetter() {
        nbSidesSetter = new JComboBox<>();
        for (int i = 3; i <= 16; ++i)
            nbSidesSetter.addItem(Integer.toString(i));

        nbSidesSetter.setBounds(125, 5, 50, 26);
        nbSidesSetter.addActionListener(e -> nbOfSides = nbSidesSetter.getSelectedIndex() + 3);
        this.add(nbSidesSetter);
    }

    private void initLabelShapes() {
        JLabel labelFromTextField = new JLabel("Number of shapes:");
        labelFromTextField.setBounds(240, 5, 110, 26);
        this.add(labelFromTextField);
    }

    private void initShapesSetter() {
        nbShapesSetter = new JTextField();
        nbShapesSetter.setBounds(355, 5, 45, 26);
        nbShapesSetter.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    nbOfShapes = Integer.valueOf(nbShapesSetter.getText());
                } catch (Exception parseException) {
                    nbOfShapes = 1;
                }
            }
        });
        this.add(nbShapesSetter);
    }

    private void initColorSetterButton() {
        colorSetter = new JButton("Pick Format");
        colorSetter.addActionListener(e -> initColorChooserFrame());
        colorSetter.setBounds(460, 5, 140, 26);
        this.add(colorSetter);
    }

    private void initDrawButton() {
        JButton drawButton = new JButton("Draw Shapes");
        drawButton.setBounds(625, 5, 140, 26);
        drawButton.addActionListener(e -> drawShapes());
        this.add(drawButton);
    }
}
