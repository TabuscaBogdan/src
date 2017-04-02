package shapes;

import javax.swing.*;
public class Canvas extends JPanel {

    JLabel title;
    public Canvas()
    {
        title=new JLabel("Sketch");
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black,3,true));
        this.setSize(800,400);
    }
    public void drawShapeAt(int x, int y) {
        /*Graphics2D graphics2D = (Graphics2D) grap;
        rand = new Random();
        graphics.setColor(new Color(rand.nextInt(800000)));*/
    }
}
