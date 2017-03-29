import javax.swing.*;
import java.awt.*;


public class Registration {

    public void render(String ID){
        JFrame window = new JFrame("Registration");
        window.setLocationRelativeTo(null);
        window.setSize(480,260);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        window.getContentPane().setLayout(new GridLayout(5,2));
       // JLabel I = new JLabel();
        window.add(new JLabel("Name"));
        window.add(new JTextField("Type your name there"));
        //window.getContentPane().add(I);
        window.add(new JLabel("SurName"));
        window.add(new JTextField("Type your surname there"));
        //window.getContentPane().add(I);
        window.add(new JLabel("ID"));
        window.add(new JLabel(ID));
        window.add(new JLabel("Insurance:"));
        window.add(new JLabel("Sitoj vietoj option button"));
        window.add(new JButton("Submit"));
        window.add(new JButton("Cancel"));
        window.setAlwaysOnTop (true);
        window.pack();
        window.setVisible(true);
        //Button with options for insurance

    }

}
