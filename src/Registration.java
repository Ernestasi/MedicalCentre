import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class Registration{
    private MedicalCenter mc;

    public Registration(MedicalCenter mc){
        this.mc = mc;
    }

    public void render(){
        JFrame window = new JFrame("Registration");
        window.setLocationRelativeTo(null);
        window.setSize(400, 250);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JPanel NamePanel = new JPanel();
        NamePanel.setLayout(new BorderLayout());
        NamePanel.add(new JLabel("Name        :"),BorderLayout.WEST);
        JTextField name = new JTextField("");
        name.setEditable(true);
        NamePanel.add(name,BorderLayout.CENTER);

        JPanel SurNamePanel = new JPanel();
        SurNamePanel.setLayout(new BorderLayout());
        SurNamePanel.add(new JLabel("SurName :"),BorderLayout.WEST);
        JTextField surName = new JTextField("");
        surName.setEditable(true);
        SurNamePanel.add(surName,BorderLayout.CENTER);

        JPanel IDPanel = new JPanel();
        IDPanel.setLayout(new BorderLayout());
        IDPanel.add(new JLabel("ID               :"),BorderLayout.WEST);
        JTextField ID = new JTextField("");
        ID.setEditable(true);
        IDPanel.add(ID,BorderLayout.CENTER);

        panel.add(NamePanel);
        panel.add(SurNamePanel);
        panel.add(IDPanel);

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
        panel.add(panel1,BorderLayout.CENTER);

        JLabel warning = new JLabel("");
        //warning.setFont(new Font("Serif", Font.BOLD, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(warning);


        //Buttons panel
        JPanel buttons = new JPanel();
        JButton submit = new JButton("Submit");
        submit.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                String nameString = name.getText();
                String surNameString = surName.getText();
                String IDString = ID.getText();
                int count = 0;
                if((IDString.length() == 11) && (nameString.length() > 1) && (surNameString.length() > 1)){
                    for(Patient p : mc.patients){
                        if(p.getId().equals(IDString)){
                            count++;
                        }
                    }
                    if(count == 0){
                        System.out.println(count);
                        int insurance = (rb0.isSelected())? 0 : (rb1.isSelected())? 1 : (rb2.isSelected())? 2 : 3;
                        Patient pat = new Patient(nameString, surNameString, IDString, insurance, " ");
                        mc.patients.add(pat);
                        //window.dispose();

                        window.setEnabled(false);


                        JFrame submitted = new JFrame("Thank you");
                        submitted.setLocation(200, 200);
                        submitted.setSize(600, 300);
                        submitted.setResizable(false);
                        submitted.isAlwaysOnTop();
                        submitted.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        submitted.setLayout(new GridLayout(0, 1));

                        JLabel name = new JLabel("Name: " + pat.getName());
                        name.setFont(new Font("Serif", Font.BOLD, 24));
                        //name.setHorizontalAlignment(SwingConstants.LEFT );
                        submitted.add(name);

                        JLabel surName = new JLabel("Surname: " + pat.getSurName());
                        surName.setFont(new Font("Serif", Font.BOLD, 24));
                        //surName.setHorizontalAlignment(SwingConstants.LEFT );
                        submitted.add(surName);

                        JLabel ID = new JLabel("ID: " + pat.getId());
                        ID.setFont(new Font("Serif", Font.BOLD, 24));
                        //ID.setHorizontalAlignment(SwingConstants.LEFT );
                        submitted.add(ID);

                        JPanel decision = new JPanel();
                        decision.setLayout(new BorderLayout());
                        JButton confirm = new JButton("Confirm");
                        confirm.addMouseListener(new MouseAdapter(){
                            @Override
                            public void mousePressed(MouseEvent e){

                                FileWriter fw;
                                try{
                                    String filename = "./src/Data/Patients.txt";
                                    fw = new FileWriter(filename, true);
                                    fw.write("\n");
                                    fw.write(nameString + "_" + surNameString + "_" + IDString + "_" + insurance + "_ _");
                                    fw.close();
                                    PatientsFrame forPatien = new PatientsFrame(mc);
                                    forPatien.mainPatientFrame();
                                    submitted.dispose();
                                    window.dispose();
                                }catch(IOException ioe){
                                    System.err.println("IOException: " + ioe.getMessage());
                                }

                            }


                        });
                        decision.add(confirm, BorderLayout.CENTER);
                        JButton back = new JButton("Back");
                        back.addMouseListener(new MouseAdapter(){
                            @Override
                            public void mousePressed(MouseEvent e){
                                window.setEnabled(true);
                                submitted.dispose();
                            }


                        });
                        decision.add(back,BorderLayout.EAST);
                        submitted.add(decision);

                        submitted.setVisible(true);


                    }else warning.setText("  You are already on the system!");
                }else if(nameString.length() <= 1)
                    warning.setText("  Entered name is too short!");
                else if(surNameString.length() <= 1)
                    warning.setText("  Entered sur name is too short!");
                else warning.setText("  ID has to be 11 digits long!");


            }
        });
        JButton cancel = new JButton("Back");
        cancel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                PatientsFrame goBack = new PatientsFrame(mc);
                goBack.mainPatientFrame();
                window.dispose();
            }
        });

        buttons.add(cancel);
        buttons.add(submit);
        //Buttons panel ends there

        window.add(panel,BorderLayout.NORTH);
        window.add(buttons, BorderLayout.SOUTH);
        //window.pack();
        window.pack();
        window.setVisible(true);
    }


}