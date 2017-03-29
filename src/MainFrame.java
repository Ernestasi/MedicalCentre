import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class MainFrame {


    Graphics g ;
    MedicalCenter medCent;
    JFrame frame;


    public void createWindow( MedicalCenter  medCent){
        this.medCent = medCent;

        frame = new JFrame("Medical Centre Application   ");
        frame.setLocationRelativeTo((Component)null);
        frame.setSize(640, 480);
        frame.getContentPane().setLayout(new GridLayout(6, 5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();

    }

    public void render(MedicalCenter  medCent){
        frame.add(new JLabel("Hi"));
    }


}
