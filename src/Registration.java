//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.*;

class Registration {

    void render(String ID) {
        JFrame window = new JFrame("Registration");
        window.setLocationRelativeTo((Component)null);
        window.setSize(480, 300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setLayout(new GridLayout(6, 5));
        window.add(new JLabel("  Name :"));
        window.add(new JTextField(" "));
        window.add(new JLabel("  SurName :"));
        window.add(new JTextField(" "));
        window.add(new JLabel("  ID"));
        window.add(new JTextField(" "));
        window.add(new JLabel(" Insurance:"));

        JRadioButton rb1 = new JRadioButton("National", false);
        JRadioButton rb2 = new JRadioButton("Personal", false);
        JRadioButton rb3 = new JRadioButton("Both", false);
        JRadioButton rb0 = new JRadioButton("None", true);
        ButtonGroup group = new ButtonGroup();
        group.add(rb0);
        group.add(rb1);
        group.add(rb2);
        group.add(rb3);
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

    /*public void tick(){
        if( rb0.isSelected()){
            rb1.setSelected(false);
            rb2.setSelected(false);
            rb3.setSelected(false);
        }
        if( rb1.isSelected()){
            rb0.setSelected(false);
            rb2.setSelected(false);
            rb3.setSelected(false);
        }
        if( rb3.isSelected()){
            rb1.setSelected(false);
            rb2.setSelected(false);
            rb0.setSelected(false);
        }
        if( rb2.isSelected()){
            rb1.setSelected(false);
            rb0.setSelected(false);
            rb3.setSelected(false);
        }
    }*/

}
