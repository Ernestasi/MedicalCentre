import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class DoctorsFrame{

    MedicalCenter mc;

    public DoctorsFrame(MedicalCenter mc){
        this.mc = mc;
    }

    public void render(){
        JFrame frame = new JFrame("Medical Centre Application for Doctors  ");
        JPanel panel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        //panel.add(scrollPane);
        //scrollPane.setLayout(new ScrollPaneLayout());
        panel.setLayout(new GridLayout(mc.doctors.size(), 1));

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo((Component)null);
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.requestFocus();


        frame.getContentPane().setLayout(new GridLayout(1, 2));


        for(Doctor d : mc.doctors){
            JButton button = new JButton( d.toString() );
            button.setPreferredSize(new Dimension( 300, 30));
            panel.add(button);
        }
        frame.getContentPane().add(new JScrollPane(panel));
        //frame.getContentPane().add(panel);
    }
}
