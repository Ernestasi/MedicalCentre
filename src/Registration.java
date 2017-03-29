//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class Registration {

    void render(String ID) {
        JFrame window = new JFrame("Registration");
        window.setLocationRelativeTo((Component)null);
        window.setSize(480, 300);
        window.setDefaultCloseOperation(1);
        window.getContentPane().setLayout(new GridLayout(6, 5));
        window.add(new JLabel("  Name :"));
        window.add(new JTextField(" "));
        window.add(new JLabel("  SurName :"));
        window.add(new JTextField(" "));
        window.add(new JLabel("  ID"));
        window.add(new JLabel(ID));
        window.add(new JLabel(" Insurance:"));
        JRadioButton rb1 = new JRadioButton("National", false);
        JRadioButton rb2 = new JRadioButton("Personal", false);
        JRadioButton rb3 = new JRadioButton("Both", false);
        JRadioButton rb0 = new JRadioButton("None", true);
        window.add(rb1);
        window.add(new JLabel(""));
        window.add(new JLabel(""));
        window.add(new JLabel(""));
        window.add(rb2);
        window.add(new JLabel(""));
        window.add(new JLabel(""));
        window.add(new JLabel(""));
        window.add(rb3);
        window.add(new JLabel(""));
        window.add(new JLabel(""));
        window.add(new JLabel(""));
        window.add(rb0);
        window.add(new JLabel(""));
        window.add(new JLabel(""));
        window.add(new JButton("Submit"));
        window.add(new JButton("Cancel"));
        window.setAlwaysOnTop(true);
        window.pack();
        window.setVisible(true);
    }
}
