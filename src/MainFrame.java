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
        //frame.setTitle("Medical Centre Application   " + medCent.dateFormat.format(medCent.cal.getTime()));
        JTextField myOutput = new JTextField(16);
        myOutput.setText("some text");
        //JTextField myOutput = new JTextField("someInitialValue", 20);s
       /* Font fnt0 = new Font("arial", Font.PLAIN, 20);
        g.setFont(fnt0);
        g.setColor(Color.black);
        g.drawString("Date: " + medCent.dateFormat.format(medCent.cal.getTime()), 50, 50);*/
      //  System.out.println("Current Date Time : " + medCent.dateFormat.format(medCent.cal.getTime()));
    }


}
