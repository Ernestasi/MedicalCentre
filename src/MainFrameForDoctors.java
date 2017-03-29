import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class MainFrameForDoctors {


    Graphics g ;
    JFrame Dframe;
    JTextField DdateF;


    public void createWindow(){

        Dframe = new JFrame("Medical Centre Application for Doctors  ");
        Dframe.setLocationRelativeTo((Component)null);
        Dframe.setSize(640, 480);
        Dframe.getContentPane().setLayout(new GridLayout(5, 1));
        Dframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dframe.setResizable(false);
        Dframe.setLocationRelativeTo(null);
        Dframe.setVisible(true);
        Dframe.requestFocus();

        DdateF = new JTextField(" Type in your ID there...");
        //dateFrame.add(new JLabel(medCent.dateFormat.format(medCent.cal.getTime())));

    }

    public void render(MedicalCenter  medCent){

        // dateF.setHorizontalAlignment(JTextField.CENTER);
        DdateF.setText(medCent.dateFormat.format(medCent.cal.getTime()));

    }


}
