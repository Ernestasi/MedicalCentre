import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrameForPatients {


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
        JButton newB = new JButton("New patient");
        newB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Registration reg = new Registration();
                reg.render();
                frame.dispose();
            }
        });
        frame.add(newB);
        JButton oldB = new JButton ("Old patient");
        frame.add(new JButton("Old patient"));

    }


}
