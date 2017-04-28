import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PatientsFrame{

    JLabel time = new JLabel();
    private MedicalCenter mc;

    public PatientsFrame(MedicalCenter mc){
        this.mc = mc;
    }

    private void logged(Patient currentPat){
        JFrame loggedIn = new JFrame("Medical Centre Application for Patients - " + currentPat.getName() + " " + currentPat.getSurName());
        loggedIn.setSize(650, 650);
        loggedIn.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loggedIn.setResizable(false);
        loggedIn.setLocationRelativeTo(null);
        loggedIn.setLayout(new BorderLayout());

        //Panels
        JTabbedPane tabs = new JTabbedPane();
        JPanel info = new JPanel(new GridLayout(2, 1, 5, 5));
        JPanel doctorsSpec = new JPanel();
        JPanel buttons = new JPanel();
        JScrollPane descriptionScrollable;
        JPanel appointmets;


        //info panel

        JLabel name = new JLabel("You are logged in as: " + currentPat.getName() + " " + currentPat.getSurName());
        name.setFont(new Font("Serif", Font.BOLD, 20));
        name.setVerticalAlignment(SwingConstants.TOP);


        JLabel insuranceType = new JLabel("Your insurance is: " + currentPat.getInsType());
        insuranceType.setFont(new Font("Serif", Font.BOLD, 20));
        insuranceType.setVerticalAlignment(SwingConstants.TOP);
        info.add(name);
        info.add(insuranceType);
        //Info labels ends there

        ArrayList<String> tempList = new ArrayList<>();
        tempList.add(mc.doctors.get(1).getSpec());
        for(Doctor d : mc.doctors){
            int temp = 0;
            for(int i = 0; i < tempList.size(); i++){
                if(d.getSpec().equals(tempList.get(i)))
                    temp++;
            }
            if(temp == 0){
                tempList.add(d.getSpec());
            }

        }

        //Description tab

        JTextArea txt3 = new JTextArea(currentPat.getDescription().replaceAll("#n#", "\n"), 14, 20);
        descriptionScrollable = new JScrollPane(txt3);
        txt3.setLineWrap(true);
        txt3.setWrapStyleWord(true);
        txt3.setEditable(false);
        //Description tab ends here

        //Doctors tab

        GridLayout mainGrid = new GridLayout(tempList.size(), 1, 10,10);
        for(int i = 0; i < tempList.size(); i++){
            String temp = tempList.get(i);
            JButton specBTN = new JButton(temp);
            specBTN.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    String currentSpec = "null";
                    ArrayList<String> currentDocs = new ArrayList<>();
                    for(Doctor d : mc.doctors){
                        if(temp.equals(d.getSpec())){
                            currentSpec = d.getSpec();
                            currentDocs.add(d.getName() + " " + d.getSurName());
                        }
                    }
                    if(currentDocs.size() > 0){
                        loggedIn.setVisible(false);
                        JFrame meetingFrame = new JFrame(currentSpec);
                        // JDialog meetingFrame = new JDialog(loggedIn, currentSpec, Dialog.ModalityType.DOCUMENT_MODAL);
                        meetingFrame.setSize(300, 500);
                        meetingFrame.setResizable(false);
                        meetingFrame.setAlwaysOnTop(true);
                        meetingFrame.setLocationRelativeTo(null);
                        meetingFrame.setDefaultCloseOperation(0);
                        meetingFrame.setLayout(new BorderLayout());
                        meetingFrame.setVisible(true);
                        meetingFrame.setLayout(new GridLayout(currentDocs.size() + 2, 1,5,5));
                        for(int i = 0; i < currentDocs.size(); i++){
                            String docName = currentDocs.get(i);
                            JButton doctorSelectBTN = new JButton(currentDocs.get(i));
                            doctorSelectBTN.addMouseListener(new MouseAdapter(){
                                @Override
                                public void mouseClicked(MouseEvent e){
                                    meetingFrame.dispose();
                                    JFrame setMeeting = new JFrame(docName);
                                    setMeeting.setSize(500, 500);
                                    setMeeting.setResizable(false);
                                    setMeeting.setAlwaysOnTop(true);
                                    setMeeting.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                                    setMeeting.setLocationRelativeTo(null);
                                    setMeeting.setLayout(new BorderLayout(10, 10));

                                    //Panels
                                    JPanel dayPNL = new JPanel();
                                    JPanel timePNL = new JPanel();
                                    JPanel buttonsPNL = new JPanel();

                                    dayPNL.setLayout(new GridLayout(1,7,5,5));
                                    timePNL.setLayout(new GridLayout(1,7,5,5));
                                    buttonsPNL.setLayout(new GridLayout(1,1));
                                    // labels end

                                    setMeeting.setVisible(true);
                                    String[] week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                                    for(Doctor d : mc.doctors){
                                        if((d.getName() + " " + d.getSurName()).equals(docName)){
                                            JComboBox dayBOX = new JComboBox(week);
                                            dayPNL.add(dayBOX);
                                            for(int i = 0; i < 7; i++)
                                                timePNL.add(new JLabel(d.getTimeDay(i)));
                                            JButton closeBTN = new JButton("Close");
                                            closeBTN.setHorizontalAlignment(SwingConstants.CENTER);
                                            closeBTN.addMouseListener(new MouseAdapter() {
                                                @Override
                                                public void mouseClicked(MouseEvent e) {
                                                    setMeeting.dispose();
                                                    loggedIn.setVisible(true);
                                                }
                                            });
                                            buttonsPNL.add(closeBTN);
                                        }
                                    }
                                    setMeeting.add(dayPNL, BorderLayout.NORTH);
                                    setMeeting.add(timePNL,BorderLayout.CENTER);
                                    setMeeting.add(buttonsPNL,BorderLayout.SOUTH);
                                }
                            });
                            meetingFrame.add(doctorSelectBTN);
                        }
                        meetingFrame.add(new JLabel());
                        JButton closeBTN = new JButton("Close");
                        closeBTN.addMouseListener(new MouseAdapter(){
                            @Override
                            public void mouseClicked(MouseEvent e){
                                meetingFrame.dispose();
                                loggedIn.setVisible(true);
                                loggedIn.setAlwaysOnTop(true);
                            }
                        });
                        meetingFrame.add(closeBTN);
                    }
                }
            });
            doctorsSpec.add(specBTN);
        }
        //Doctors tab End here


        // Appointments tab

        appointmets = new JPanel(new GridLayout(0,1,5,5));
        appointmets.setLayout(new BorderLayout());
        SimpleDateFormat appFormat = new SimpleDateFormat("YYYY-MM-dd HH:MM");
        if(currentPat.getAppointments() != null){
            for(int i = 0; i < currentPat.getAppointments().size(); i++){
                Doctor doc =mc.doctors.get(Integer.parseInt(currentPat.getDocId().get(i)));
                appointmets.add(new JLabel(appFormat.format(currentPat.getTime().get(i).getTime()) + " " + doc.toString()),BorderLayout.NORTH);
            }
            appointmets.setPreferredSize(new Dimension(600, appointmets.getComponentCount() *30));
        }else{
            appointmets.add(new JLabel("You have no appointments scheduled"));
        }
        //Appointments tab end here


        doctorsSpec.setLayout(mainGrid);

        tabs.add(descriptionScrollable, "Description");
        tabs.add(doctorsSpec, "Doctors");
        tabs.add(appointmets, "Appointments");
        //tabs.add(doctorsSpec, "Contacts");

        loggedIn.add(info, BorderLayout.NORTH);
        loggedIn.add(tabs, BorderLayout.CENTER);
        loggedIn.add(buttons, BorderLayout.SOUTH);//
        //Panels ends there

        //buttuns panel
        buttons.setLayout(new BorderLayout(50, 50));
        JButton signOutBTN = new JButton("Sign out");
        signOutBTN.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                mainPatientFrame();
                loggedIn.dispose();
            }
        });

        JButton newAppointmentBTN = new JButton("New Appointment");
        newAppointmentBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });


        buttons.add(signOutBTN, BorderLayout.EAST);
        buttons.add(newAppointmentBTN,BorderLayout.WEST);
        //Buttons ends there

        //loggedIn.pack();
        loggedIn.setVisible(true);

    }

    void mainPatientFrame(){

        JFrame frame = new JFrame("Medical Centre Application for Patients  ");
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 200);
        frame.getContentPane().setLayout(new GridLayout(1, 2));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.requestFocus();
        JButton newB = new JButton("New patient");
        newB.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                Registration reg = new Registration(mc);
                reg.render();
                frame.dispose();
            }
        });
        frame.add(newB);
        JButton oldB = new JButton("Registered patient");
        oldB.addMouseListener(new MouseAdapter(){
            String ID;

            @Override
            public void mouseClicked(MouseEvent e){
                frame.dispose();


                JFrame securityID = new JFrame("Enter your security ID");

                securityID.setLocationRelativeTo(null);
                securityID.setAlwaysOnTop(true);
                securityID.setPreferredSize(new Dimension(300, 150));
                securityID.setSize(300, 150);
                securityID.setLayout(new GridLayout(4, 1));
                securityID.setResizable(false);
                securityID.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                securityID.requestFocus();
                securityID.setVisible(true);

                JPanel top = new JPanel();
                top.setLayout(new BorderLayout());

                JLabel test = new JLabel("Enter your ID");
                test.setHorizontalAlignment(SwingConstants.CENTER);
                top.add(test, BorderLayout.WEST);

                JButton regBTN = new JButton("Register");
                regBTN.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        Registration reg = new Registration(mc);
                        reg.render();
                        securityID.dispose();
                    }
                });
                regBTN.setEnabled(false);
                regBTN.setVisible(false);
                regBTN.setHorizontalAlignment(SwingConstants.RIGHT);
                top.add(regBTN, BorderLayout.EAST);

                securityID.add(top);
                JPanel jp = new JPanel();
                JTextField textID = new JTextField(1);
                jp.add(textID);
                securityID.add(textID);
                JButton submit = new JButton("Submit");
                submit.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        ID = textID.getText();
                        if(ID != null){
                            int check = 0;
                            for(Patient p : mc.patients){
                                if((ID.length() == 11) && (ID.matches("[0-9]+"))){
                                    if(ID.equals(p.getId())){
                                        securityID.dispose();
                                        logged(p);
                                        check++;
                                    }
                                    if(check == 0){
                                        test.setText("No such ID");
                                        //regBTN.setBackground(Color.red);
                                        regBTN.setEnabled(true);
                                        regBTN.setVisible(true);


                                    }
                                }else if(!ID.matches("[0-9]+"))
                                    test.setText("ID can contain only digits!");
                                else if(ID.length() != 11)
                                    test.setText("ID has to be 11 digits long!");


                            }

                        }

                    }
                });
                securityID.add(submit);
                JButton back = new JButton("Back");
                back.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        PatientsFrame goBack = new PatientsFrame(mc);
                        securityID.dispose();
                        goBack.mainPatientFrame();
                    }
                });
                securityID.add(back);

            }
        });
        frame.add(oldB);
    }


    public void tick(){

    }
}