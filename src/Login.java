import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Login {

    MedicalCenter mc;

    public Login(MedicalCenter mc){
        this.mc = mc;
    }

   public int render() {

        JFrame window = new JFrame("Login");
        window.setLocationRelativeTo((Component)null);
        window.setPreferredSize(new Dimension(500   , 300 ));
        window.setSize(500, 300);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setLayout(new GridLayout(3, 1));
        window.pack();

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
                DoctorsFrame forDoc = new DoctorsFrame(mc);
                forDoc.render();
                window.dispose();
            }

        });
        window.add(doctors);

        JButton patients = new JButton("I am patient"); //Doctors button
        patients.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                PatientsFrame forPatien = new PatientsFrame();
                forPatien.render();
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
        int loginStatus;
        return loginStatus = 1;                                              // 0 uzdaro programa / 1 mainFrameForDoctors /2 mainFrameForPatients
    }                                                                       // /3 Registration o tada grazinam atgal i Login
}
