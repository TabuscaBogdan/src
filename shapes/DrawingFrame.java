package shapes; /**
 * Created by Bogdan on 01.04.2017.
 */
import javax.swing.*;

public class DrawingFrame {

    public static void create_window()
    {
        JFrame frame=new JFrame("Draw your Shape");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setIconImage(new ImageIcon(imgURL).getImage());
        frame.getContentPane().
        frame.pack();
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                create_window();
            }
        });

    }

}
