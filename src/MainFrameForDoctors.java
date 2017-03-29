import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrameForDoctors {
    void render(){
        JFrame frame = new JFrame("Medical Centre Application for Doctors  ");
        frame.setLocationRelativeTo((Component)null);
        frame.setPreferredSize(new Dimension(500   , 200 ));
        frame.setSize(500, 200);
        frame.getContentPane().setLayout(new GridLayout(1, 2));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();
        frame.add(new Button("TO DO SOME SHIT HERE"));
    }
}
