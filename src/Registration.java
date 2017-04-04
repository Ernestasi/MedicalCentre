import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class Registration {
    MedicalCenter mc;
    public Registration(MedicalCenter mc){
        this.mc = mc;
    }

    public void render() {
        JFrame window = new JFrame("Registration");
        window.setLocationRelativeTo(null);
        window.setSize(480, 300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setLayout(new GridLayout(0, 1));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0, 5,5));
        panel.add(new JLabel("Name :"));
        JTextField name = new JTextField(" ");
        panel.add(name);
        panel.add(new JLabel("SurName :"));
        JTextField surName = new JTextField(" ");
        panel.add(surName);
        panel.add(new JLabel("ID:"));
        JTextField ID = new JTextField(" ");
        panel.add(ID);
        window.add(panel);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(1, 0));
        panel1.add(new JLabel(" Insurance:"));
        JRadioButton rb1 = new JRadioButton("National", false);
        JRadioButton rb2 = new JRadioButton("Personal", false);
        JRadioButton rb3 = new JRadioButton("Both", false);
        JRadioButton rb0 = new JRadioButton("None", true);
        ButtonGroup group = new ButtonGroup();
        group.add(rb0);
        group.add(rb1);
        group.add(rb2);
        group.add(rb3);
        panel1.add(rb1);
        panel1.add(rb2);
        panel1.add(rb3);
        panel1.add(rb0);
        window.add(panel1);

        JLabel warning = new JLabel("");
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        window.add(warning);
        JButton submit = new JButton("Submit");
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String nameString = name.getText();
                String surNameString = surName.getText();
                String IDString = ID.getText();
                int count = 0;
                for(Patient p : mc.patients){
                    if(p.getId().equals(IDString)){
                        count++;
                    }
                }
                if(count == 0){
                    System.out.println(count);
                    String filename = "./src/Data/Patients.txt";
                    try{
                        FileWriter fw = new FileWriter(filename,true);
                        fw.write(nameString + "," +  surNameString + "," +  IDString + "," + "0");
                        fw.write("\n");
                        fw.close();
                        Patient pat = new Patient(nameString, surNameString, IDString, 0);
                        mc.patients.add(pat);
                        window.dispose();
                        JFrame submited = new JFrame("Thank you");
                        submited.setLocationRelativeTo(null);
                        submited.setSize(480, 300);
                        submited.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        submited.setVisible(true);
                        submited.add(new JLabel("SUBMITED NICE AND GOOD INFO"));
                    } catch(IOException ioe)
                    {
                        System.err.println("IOException: " + ioe.getMessage());
                    }
                }
                else warning.setText("You are already on the system!");

            }
        });
        window.add(submit);
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
//