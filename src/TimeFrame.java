import javax.swing.*;
import java.awt.*;

public class TimeFrame {

    MedicalCenter mc;
    JLabel time;

    public TimeFrame(MedicalCenter mc){
        this.mc = mc;
    }

    public void OpenTime(){
        JFrame window = new JFrame("Time Controll");
        window.setDefaultCloseOperation(0);
        //window.set
        window.setSize(320, 180);
        window.setResizable(false);
        window.getContentPane().setLayout(new GridLayout(3, 3));
        window.setVisible(true);
        //window.pack();
        time = new JLabel(mc.dateFormat.format(mc.cal.getTime()));
        window.add(new JLabel("  Current Time:"));
        window.add(time);
        window.add(new JButton("plus day"));
        window.add(new JButton("plus week"));
        window.add(new JButton("plus month"));
        window.add(new JButton("plus year"));

    }

    public void tick(){
        time.setText((mc.dateFormat.format(mc.cal.getTime())));
    }
}
