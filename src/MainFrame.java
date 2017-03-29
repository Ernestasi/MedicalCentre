import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class MainFrame {


    Graphics g ;
    MedicalCenter medCent;
    JFrame frame;
    JTextField dateF;


    public void createWindow( MedicalCenter  medCent){
        this.medCent = medCent;

        frame = new JFrame("Medical Centre Application   ");
        frame.setLocationRelativeTo((Component)null);
        frame.setSize(640, 480);
        frame.getContentPane().setLayout(new GridLayout(5, 1));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();

        dateF = new JTextField(" Type in your ID there...");
        //dateFrame.add(new JLabel(medCent.dateFormat.format(medCent.cal.getTime())));

    }

    public void render(MedicalCenter  medCent){

        dateF.setHorizontalAlignment(JTextField.CENTER);
        dateF.setText(medCent.dateFormat.format(medCent.cal.getTime()));

    }


}
