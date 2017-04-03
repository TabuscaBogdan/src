package shapes;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Octavian on 4/3/2017.
 */

class FunctionCanvas extends JPanel {

    private Toolbar toolbar;
    private JPanel inputPanel;
    private JPanel drawingPanel;
    private Calculable calculator;
    private int drawingScale = 10;

    FunctionCanvas() {
        super(new BorderLayout());

        initInputPanel();
        initDrawPanel();

        this.add(inputPanel, BorderLayout.NORTH);
        this.add(drawingPanel, BorderLayout.CENTER);
    }

    private void drawGraph() {
        Graphics2D g2 = (Graphics2D) drawingPanel.getGraphics();

        int drawingWidth = drawingPanel.getWidth();
        int drawingHeight = drawingPanel.getHeight();

        g2.clearRect(0, 0, drawingWidth, drawingHeight);
        g2.drawLine(0, drawingHeight / 2, drawingWidth, drawingHeight / 2);
        g2.drawLine(drawingWidth / 2, 0, drawingWidth / 2, drawingHeight);

        g2.setStroke(new BasicStroke(toolbar.getLineWidth()));
        g2.setColor(toolbar.getColor());

        double stepY = drawingHeight / drawingScale;
        double stepX = drawingWidth / drawingScale;

        for (double x = -drawingScale; x <= drawingScale; x += drawingScale / stepX / 4) {

            calculator.setVariable("x", x);
            double x1 = x * stepX + drawingWidth / 2;
            double y1 = calculator.calculate() * stepY;
            y1 = drawingHeight - y1 - drawingHeight / 2;

            calculator.setVariable("x", x + drawingScale / stepX / 4);
            double x2 = (x + drawingScale / stepX / 4) * stepX + drawingWidth / 2;
            double y2 = calculator.calculate() * stepY;
            y2 = drawingHeight - y2 - drawingHeight / 2;

            g2.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        }
    }

    private void initInputPanel() {
        inputPanel = new JPanel(new BorderLayout());
        JLabel labelFunction = new JLabel("Write a math function:");
        JButton drawButton = new JButton("Draw Function");
        JTextField functionField = new JTextField();
        functionField.setText("f(x) = ");

        drawButton.addActionListener(e -> drawGraph());

        functionField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(functionField.getText().equals("f(x) = "))
                    functionField.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        functionField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    ExpressionBuilder expression = new ExpressionBuilder(functionField.getText());
                    expression.withVariable("x", 0);
                    calculator = expression.build();
                    functionField.setBackground(new Color(127, 255, 127));
                } catch (Exception parseException) {
                    functionField.setBackground(new Color(255, 127, 127));
                }
            }
        });

        inputPanel.add(labelFunction, BorderLayout.NORTH);
        inputPanel.add(functionField, BorderLayout.CENTER);
        inputPanel.add(drawButton, BorderLayout.SOUTH);
    }

    private void initDrawPanel() {
        drawingPanel = new JPanel();
        drawingPanel.addMouseWheelListener(e -> {
            drawingScale += (drawingScale > 1 ? e.getWheelRotation() : 1);
            drawGraph();
        });
    }

    void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }
}
