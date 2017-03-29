import javax.swing.*;
import java.awt.*;

public class TimeFrame {

    public void OpenTime(){
        JFrame window = new JFrame("Time Controll");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(260, 180);
        window.setResizable(false);
        window.getContentPane().setLayout(new GridLayout(3, 1));
        window.setVisible(true);
        //window.pack();

        //window.add(new JLabel("Time:" + medCent.dateFormat.format(medCent.cal.getTime())));

    }
}
