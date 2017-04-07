import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;//

public class Registration {
    private MedicalCenter mc;
    public Registration(MedicalCenter mc){
        this.mc = mc;
    }

    public void render() {
        JFrame window = new JFrame("Registration");
        window.setLocationRelativeTo(null);
        window.setSize(500, 200);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setLayout(new GridLayout(0, 1));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0, 5,5));
        panel.add(new JLabel("Name :"));
        JTextField name = new JTextField("");
        panel.add(name);
        panel.add(new JLabel("SurName :"));
        JTextField surName = new JTextField("");
        panel.add(surName);
        panel.add(new JLabel("ID:"));
        JTextField ID = new JTextField("");
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
                if((IDString.length()==11) && (nameString.length() > 1) && (surNameString.length() > 1)){
                    for(Patient p : mc.patients){
                        if(p.getId().equals(IDString)){
                            count++;
                        }
                    }
                    if(count == 0){
                        System.out.println(count);
                        Patient pat = new Patient(nameString, surNameString, IDString, 0);
                        mc.patients.add(pat);
                        //window.dispose();
                        JFrame submited = new JFrame("Thank you");
                        submited.setLocation(200, 200);
                        submited.setSize(600, 300);
                        submited.setResizable(false);
                        submited.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        submited.setLayout(new GridLayout(0,1));

                        JLabel name = new    JLabel("Name: "+ pat.getName());
                        name.setFont(new Font("Serif", Font.BOLD, 24));
                        //name.setHorizontalAlignment(SwingConstants.LEFT );
                        submited.add(name);

                        JLabel surName = new JLabel("Surname: "+ pat.getSurName());
                        surName.setFont(new Font("Serif", Font.BOLD, 24));
                        //surName.setHorizontalAlignment(SwingConstants.LEFT );
                        submited.add(surName);

                        JLabel ID = new      JLabel("ID: "+ pat.getId());
                        ID.setFont(new Font("Serif", Font.BOLD, 24));
                        //ID.setHorizontalAlignment(SwingConstants.LEFT );
                        submited.add(ID);

                        JPanel decision = new JPanel();
                        decision.setLayout(new FlowLayout());
                        JButton confirm = new JButton("Confirm");
                        confirm.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    FileWriter fw;
                                    try {
                                        String filename = "./src/Data/Patients.txt";
                                        fw = new FileWriter(filename,true);
                                        fw.write("\n");
                                        fw.write(nameString + "," +  surNameString + "," +  IDString + "," + "0");
                                        fw.close();
                                        PatientsFrame forPatien = new PatientsFrame(mc);
                                        forPatien.render();
                                        submited.dispose();
                                        window.dispose();
                                    }
                                    catch(IOException ioe)
                                    {
                                        System.err.println("IOException: " + ioe.getMessage());
                                    }

                                }



                            });
                            decision.add(confirm);
                            JButton back = new JButton("Back");
                            back.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mousePressed(MouseEvent e) {
                                    Registration reg = new Registration(mc);
                                    reg.render();
                                    submited.dispose();
                                }


                            });
                        decision.add(back);
                        submited.add(decision);

                        submited.setVisible(true);


                    }
                    else warning.setText("  You are already on the system!");
                }
                else if(nameString.length() <= 1)
                    warning.setText("  Entered name is too short!");
                else if(surNameString.length() <= 1)
                    warning.setText("  Entered sur name is too short!");
                else warning.setText("  ID has to be 11 digits long!");


            }
        });
        window.add(submit);
        JButton cancel = new JButton("Back");
                cancel.addMouseListener(new MouseAdapter() {
@Override
public void mouseClicked(MouseEvent e) {
        PatientsFrame goBack = new PatientsFrame(mc);
        goBack.render();
        window.dispose();
        }
        });
        window.add(cancel);
        window.setAlwaysOnTop(true);
        window.pack();
        window.setVisible(true);
        }


        }