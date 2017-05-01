import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class PatientsFrame {

    private MedicalCenter mc;
    Calendar scheduleTime;
    Methods methods = new Methods();
    DataMethods dataMethods = new DataMethods();
    JTabbedPane tabs = new JTabbedPane();

    public PatientsFrame(MedicalCenter mc) {
        this.mc = mc;
    }

    private void logged(Patient currentPat) {
        scheduleTime = (Calendar) mc.cal.clone();

        warningFrame(mc, currentPat);

        JFrame loggedIn = new JFrame("Medical Centre Application for Patients - " + currentPat.getName() + " " + currentPat.getSurName());
        loggedIn.setSize(650, 650);
        loggedIn.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loggedIn.setResizable(false);
        loggedIn.setLocationRelativeTo(null);
        loggedIn.setLayout(new BorderLayout());

        //Panels
        JPanel info = new JPanel(new GridLayout(2, 1, 5, 5));
        JPanel doctorsSpec = new JPanel();
        JPanel buttons = new JPanel();
        JScrollPane descriptionScrollable;


        //info panel

        JLabel name = new JLabel("You are logged in as: " + currentPat.getName() + " " + currentPat.getSurName());
        name.setFont(new Font("Serif", Font.BOLD, 20));
        name.setVerticalAlignment(SwingConstants.TOP);


        JLabel insuranceType = new JLabel("Your insurance is: " + currentPat.getInsType());
        insuranceType.setFont(new Font("Serif", Font.BOLD, 20));
        insuranceType.setVerticalAlignment(SwingConstants.TOP);
        info.add(name);
        info.add(insuranceType);
        //Info labels ends theree

        ArrayList<String> tempList = new ArrayList<>();
        tempList.add(mc.doctors.get(1).getSpec());
        for (Doctor d : mc.doctors) {
            int temp = 0;
            for (int i = 0; i < tempList.size(); i++) {
                if (d.getSpec().equals(tempList.get(i)))
                    temp++;
            }
            if (temp == 0) {
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

        GridLayout mainGrid = new GridLayout(tempList.size(), 1, 10, 10);
        for (int i = 0; i < tempList.size(); i++) {
            String temp = tempList.get(i);
            JButton specBTN = new JButton(temp);
            specBTN.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String currentSpec = "null";
                    ArrayList<String> currentDocs = new ArrayList<>();
                    for (Doctor d : mc.doctors) {
                        if (temp.equals(d.getSpec())) {
                            currentSpec = d.getSpec();
                            currentDocs.add(d.getName() + " " + d.getSurName());
                        }
                    }
                    if (currentDocs.size() > 0) {
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
                        meetingFrame.setLayout(new GridLayout(currentDocs.size() + 2, 1, 5, 5));
                        for (int i = 0; i < currentDocs.size(); i++) {
                            String docName = currentDocs.get(i);
                            JButton doctorSelectBTN = new JButton(currentDocs.get(i));
                            doctorSelectBTN.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    JDialog setMeeting = new JDialog(meetingFrame, docName, Dialog.ModalityType.DOCUMENT_MODAL);
                                    setMeeting.setSize(500, 500);
                                    setMeeting.setResizable(false);
                                    setMeeting.setDefaultCloseOperation(1);
                                    setMeeting.setLocationRelativeTo(null);
                                    //panels
                                    JPanel dayPNL = new JPanel();
                                    for (Doctor doc : mc.doctors) {
                                        if ((doc.getName() + " " + doc.getSurName()).equals(docName)) {
                                            dayPNL = methods.selectTimePanel(doc, currentPat, scheduleTime, mc);
                                        }
                                    }

                                    setMeeting.add(dayPNL, BorderLayout.NORTH);
                                    setMeeting.setVisible(true);
                                }
                            });
                            meetingFrame.add(doctorSelectBTN);
                        }
                        meetingFrame.add(new JLabel());
                        JButton closeBTN = new JButton("Close");
                        closeBTN.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                meetingFrame.dispose();
                                loggedIn.setVisible(true);
                                int selectedTab = tabs.getSelectedIndex();
                                tabs.remove(2);
                                tabs.add(appointmentsTab(currentPat), "Appointments");
                                tabs.setSelectedIndex(selectedTab);
                            }
                        });
                        meetingFrame.add(closeBTN);
                    }
                }
            });
            doctorsSpec.add(specBTN);
        }
        //Doctors tab End here


        doctorsSpec.setLayout(mainGrid);

        tabs.add(descriptionScrollable, "Description");
        tabs.add(doctorsSpec, "Doctors");
        tabs.add(appointmentsTab(currentPat), "Appointments");

        loggedIn.add(info, BorderLayout.NORTH);
        loggedIn.add(tabs, BorderLayout.CENTER);
        loggedIn.add(buttons, BorderLayout.SOUTH);//
        //Panels ends there

        //buttuns panel
        buttons.setLayout(new BorderLayout(50, 50));
        JButton signOutBTN = new JButton("Sign out");
        signOutBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPatientFrame();
                loggedIn.dispose();
            }
        });

        buttons.add(signOutBTN, BorderLayout.CENTER);
        //Buttons ends there

        //loggedIn.pack();
        loggedIn.setVisible(true);

    }

    void mainPatientFrame() {

        JFrame frame = new JFrame("Medical Centre Application for Patients  ");
        frame.setLocationRelativeTo(null);
        frame.setSize(300, 150);
        frame.getContentPane().setLayout(new GridLayout(1, 2));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.requestFocus();
        JButton newB = new JButton("New patient");
        newB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Registration reg = new Registration(mc);
                reg.render();
                frame.dispose();
            }
        });
        frame.add(newB);
        JButton oldB = new JButton("Registered patient");
        oldB.addMouseListener(new MouseAdapter() {
            String ID;

            @Override
            public void mouseClicked(MouseEvent e) {
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
                regBTN.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
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
                submit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        ID = textID.getText();
                        if (ID != null) {
                            int check = 0;
                            for (Patient p : mc.patients) {
                                if ((ID.length() == 11) && (ID.matches("[0-9]+"))) {
                                    if (ID.equals(p.getId())) {
                                        securityID.dispose();
                                        logged(p);
                                        check++;
                                    }
                                    if (check == 0) {
                                        test.setText("No such ID");
                                        //regBTN.setBackground(Color.red);
                                        regBTN.setEnabled(true);
                                        regBTN.setVisible(true);


                                    }
                                } else if (!ID.matches("[0-9]+"))
                                    test.setText("ID can contain only digits!");
                                else if (ID.length() != 11)
                                    test.setText("ID has to be 11 digits long!");


                            }

                        }

                    }
                });
                securityID.add(submit);
                JButton back = new JButton("Back");
                back.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
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

    public void warningFrame(MedicalCenter mc, Patient currentPat) {
        Calendar time = (Calendar) mc.cal.clone();
        time.add(Calendar.DAY_OF_MONTH, 7);
        int countMeetingsWeek = 0;
        int appointmentsCount = 0;
        if (currentPat.time != null) {
            for (Calendar c : currentPat.time) {
                if (c.after(mc.cal) && c.before(time))
                    countMeetingsWeek++;
                appointmentsCount = currentPat.appointments.toString().length() / 20;
            }
        }
        boolean needsVisit = true;
        if (appointmentsCount != 0) {
            int position = 1;
            for (int i = 0; i < appointmentsCount; i++) {
                char docIDChar = currentPat.appointments.toString().charAt(position);
                String docID = String.valueOf(docIDChar);
                position = position + 20;

                for (Doctor d : mc.doctors) {
                    String testDoc = String.valueOf(d.getId());
                    if ((docID.equals(testDoc)) && (!d.getSpec().equals("family doctor"))) {
                        needsVisit = false;
                    }
                }
            }
        }
        if ((needsVisit || (countMeetingsWeek != 0))) {
            JFrame reminderFrame = new JFrame("Reminder");
            reminderFrame.setSize(1010, 100);
            reminderFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            reminderFrame.setResizable(false);
            reminderFrame.setLocationRelativeTo(null);
            reminderFrame.setAlwaysOnTop(true);
            reminderFrame.getContentPane().setBackground(Color.pink);
            reminderFrame.setLayout(new GridLayout(1, 1));
            //labels
            JLabel reminderLabel = new JLabel("You have an appointment in upcoming week, check appointments tab.");
            reminderLabel.setFont(new Font("TimesRoman", Font.BOLD, 30));
            JLabel familyDoctorLabel = new JLabel("You haven't visited family doctor yet, please register.");
            familyDoctorLabel.setFont(new Font("TimesRoman", Font.BOLD, 30));
            //labels end
            //checking if frame needs to be configured or even visible.
            if (countMeetingsWeek != 0)
                reminderFrame.add(reminderLabel);
            if (!needsVisit && (countMeetingsWeek != 0)) {
                reminderFrame.setSize(1010, 200);
                reminderFrame.setLayout(new GridLayout(2, 1));

                reminderFrame.add(familyDoctorLabel);
            }
            if (!needsVisit && (countMeetingsWeek == 0)) {
                reminderFrame.add(familyDoctorLabel);
            }
            if (appointmentsCount == 0) {
                reminderFrame.add(familyDoctorLabel);
            }
            reminderFrame.setVisible(true);
        }
    }

    public void tick() {
    }

    private JPanel appointmentsTab(Patient currentPat) {
        JPanel returnP = new JPanel(new GridLayout());
        JPanel tempPan = new JPanel(new GridLayout(0,2,1,1));

        SimpleDateFormat appFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        if (currentPat.getAppointments() != null) {
            for (int i = 0; i < currentPat.getAppointments().size(); i++) {
                Doctor doc = mc.doctors.get(Integer.parseInt(currentPat.getDocId().get(i)));
                Calendar lastDate = currentPat.time.get(i);
                tempPan.add(new JLabel(appFormat.format(currentPat.getTime().get(i).getTime()) + " " + doc.toString()));
                JButton removeBut = new JButton("remove");
                int a = i;
                removeBut.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        for (Patient p : mc.patients) {
                            if (currentPat == p) {
                                for (int k = 0; k < currentPat.appointments.size(); k++) {

                                    if (lastDate.toString().equals(currentPat.time.get(k).toString())) {
                                        p.removeAppointment(k);
                                        dataMethods.updateDatesFile(mc.patients);
                                        int selectedTab = tabs.getSelectedIndex();
                                        tabs.remove(2);
                                        tabs.add(appointmentsTab(currentPat), "Appointments");
                                        tabs.setSelectedIndex(selectedTab);
                                    }
                                }
                            }
                        }
                    }
                });
                tempPan.add(removeBut);
            }
        } else {
            tempPan.add(new JLabel("You have no appointments scheduled"));
        }

        JScrollPane scrollPan = new JScrollPane(tempPan);
        returnP.add(scrollPan);
        return returnP;

    }
}