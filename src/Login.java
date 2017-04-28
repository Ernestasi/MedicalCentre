import javafx.stage.Modality;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import javax.swing.*;

public class Login {

    MedicalCenter mc;
    Methods methods = new Methods();
    public Login(MedicalCenter mc) {
        this.mc = mc;
    }

    public void render() {
        JFrame window = new JFrame("Login");
        window.setLocationRelativeTo(null);
        window.setPreferredSize(new Dimension(500, 300));
        window.setSize(500, 300);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setLayout(new GridLayout(3, 1));
        window.pack();

        JButton doctors = new JButton("I am doctor"); //Doctors button
        doctors.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                window.setVisible(false);
                JDialog jDialog = new JDialog(window, "Doctor", Dialog.ModalityType.DOCUMENT_MODAL);
                jDialog.setDefaultCloseOperation(0);
                jDialog.setResizable(false);
                jDialog.setLocation(window.getX() + 50, window.getY() + 50);
                jDialog.setSize(new Dimension(200, 130));
                jDialog.setLayout(new GridLayout(0, 1, 1, 1));
                JPanel jPanel = new JPanel(new GridBagLayout());
                jPanel.add(new JLabel("Enter your ID:    "));
                JTextField jText = new JTextField(7);
                jPanel.add(jText);
                jDialog.add(jPanel);

                JPanel jPanel1 = new JPanel(new GridBagLayout());
                JButton confirmBut = new JButton("Confirm");
                confirmBut.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (jText.getText().matches("^[0-9]$") ) {
                            jDialog.dispose();
                            mc.docFrame = new DoctorsFrame(mc, Integer.parseInt(jText.getText()));
                            mc.docFrame.init();
                            window.dispose();
                        }
                    }
                });
                JButton cancelBut = new JButton(" Cancel");
                cancelBut.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        jDialog.dispose();
                        window.setVisible(true);
                    }
                });
                jPanel1.add(confirmBut);
                jPanel1.add(new JLabel(" "));
                jPanel1.add(cancelBut);
                jDialog.add(jPanel1);
                jDialog.setVisible(true);

            }

        });
        window.add(doctors);

        JButton patients = new JButton("I am patient"); //Doctors button
        patients.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mc.patFrame = new PatientsFrame(mc);
                mc.patFrame.mainPatientFrame();
                window.dispose();
            }


        });
        window.add(patients);

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

    }
}