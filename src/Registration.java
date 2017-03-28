import javax.swing.*;
import java.awt.*;


public class Registration {

    public void render(String ID){
        JFrame window = new JFrame("Registration");
        window.setSize(480,320);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        window.getContentPane().setLayout(new GridLayout(4,4));
        JLabel I = new JLabel("Name");
        window.getContentPane().add(I);
        window.add(new JTextField("Type your name there"));
        window.add(new JLabel("Surname"));
        window.getContentPane().add(I);
        window.add(new JTextField("Type your surname there"));
        window.add(new JLabel("ID"));
        window.add(new JLabel(ID));
        //Button with options for insurance

    }

}
