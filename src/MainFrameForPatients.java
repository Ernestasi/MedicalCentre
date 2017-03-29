import javax.swing.*;
import java.awt.*;

public class MainFrameForPatients {


    Graphics g ;
    MedicalCenter PmedCent;
    JFrame Pframe;
    JTextField PdateF;


    public void createWindow( MedicalCenter  medCent){
        this.PmedCent = medCent;

        Pframe = new JFrame("Medical Centre Application for Patients  ");
        Pframe.setLocationRelativeTo((Component)null);
        Pframe.setSize(640, 480);
        Pframe.getContentPane().setLayout(new GridLayout(5, 1));
        Pframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Pframe.setResizable(false);
        Pframe.setLocationRelativeTo(null);
        Pframe.setVisible(true);
        Pframe.requestFocus();

        PdateF = new JTextField(" Type in your ID there...");
        //dateFrame.add(new JLabel(medCent.dateFormat.format(medCent.cal.getTime())));

    }

    public void render(MedicalCenter  medCent){

        // dateF.setHorizontalAlignment(JTextField.CENTER);
        PdateF.setText(medCent.dateFormat.format(medCent.cal.getTime()));

    }


}
