//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class Login {

    int render() {

        JFrame window = new JFrame("Login");
        window.setLocationRelativeTo((Component)null);
        window.setPreferredSize(new Dimension(500   , 300 ));
        window.setSize(500, 300);
        window.setResizable(false);
        window.setDefaultCloseOperation(1);
        window.getContentPane().setLayout(new GridLayout(3, 2));


        /*
        JLabel label = new JLabel("I am doctor"); //Label : For doctors
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        window.add(label);

        JLabel label1 = new JLabel("I am patient"); //Label : For patient
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setVerticalAlignment(JLabel.CENTER);
        window.add(label1);*/


        JButton doctors = new JButton("I am doctor"); //Doctors button
        //doctors.setSize(150,75);
        doctors.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int loginStatus = 1;
                window.dispose();
            }

        });
        window.add(doctors);

        JButton patients = new JButton("I am patient"); //Doctors button
        //patients.setSize(150,75);
        patients.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int loginStatus = 2;
                window.dispose();
            }



        });
        window.add(patients);

        window.add(new JLabel(""));
        final JTextField ID = new JTextField(" Type in your ID there...");
        ID.setHorizontalAlignment(JTextField.CENTER);
        ID.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ID.setText("");
                //ID.setBackground(Color);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                    if(ID.equals("")){
                         ID.setText("Type in your ID there");

                    }


            }
        });
        window.add (ID);

        window.add(new JLabel(""));


        JButton loginExit = new JButton("Exit");
        window.add(loginExit);
        loginExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        window.setAlwaysOnTop(true);
        window.pack();
        window.setVisible(true);
        int loginStatus;
        return loginStatus = 1;                                              // 0 uzdaro programa / 1 mainFrameForDoctors /2 mainFrameForPatients
    }                                                                       // /3 Registration o tada grazinam atgal i Login
}
