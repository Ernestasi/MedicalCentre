import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PatientsFrame {

    JLabel time = new JLabel();

    private MedicalCenter mc;
    public PatientsFrame(MedicalCenter mc){
        this.mc = mc;
    }

    private void logged (Patient p) {

        JFrame loggedIn = new JFrame();
        loggedIn.setSize(650, 650);
        loggedIn.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loggedIn.setResizable(false);
        loggedIn.setLocationRelativeTo(null);
        loggedIn.setLayout(new BorderLayout());

        //Panels
        JPanel info = new JPanel();
        JPanel main = new JPanel();
        JPanel buttons = new JPanel();


        ArrayList<String> tempList = new ArrayList<String>();
        tempList.add(mc.doctors.get(1).getSpec());
        for(Doctor d: mc.doctors){
            int temp = 0;
            for(int i = 0; i < tempList.size(); i++){
                if(d.getSpec().equals(tempList.get(i)))
                    temp++;
            }
            if(temp == 0){
                tempList.add(d.getSpec());

            }

        }
        //Specialization tab
        GridLayout mainGrid = new GridLayout(tempList.size(),1);
        for (int i = 0; i < tempList.size(); i++){
            String temp = tempList.get(i);
            JButton specBTN = new JButton(temp);
            specBTN.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String currentSpec = "null";
                    ArrayList<String> currentDocs = new ArrayList<>();
                    for(Doctor d : mc.doctors){
                        if(temp.equals(d.getSpec())){
                            currentSpec = d.getSpec();
                            currentDocs.add(d.getName() + " " + d.getSurName());
                        }
                    }
                    if(currentDocs.size() > 0){
                        JFrame meetingFrame = new JFrame(currentSpec);
                        meetingFrame.setSize(300, 500);
                        meetingFrame.setResizable(false);
                        meetingFrame.setLocationRelativeTo(null);
                        meetingFrame.setLayout(new BorderLayout());
                        meetingFrame.setVisible(true);
                        meetingFrame.setLayout(new GridLayout(currentDocs.size()+2, 1));
                        for(int i = 0; i < currentDocs.size(); i++){
                            JButton doctorSelectBTN = new JButton(currentDocs.get(i));
                            String docName = currentDocs.get(i);
                            doctorSelectBTN.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    meetingFrame.dispose();
                                    JFrame setMeeting = new JFrame(docName);
                                    setMeeting.setSize(500, 500);
                                    setMeeting.setResizable(false);
                                    setMeeting.setLocationRelativeTo(null);
                                    setMeeting.setLayout(new GridLayout(2,7));
                                    setMeeting.setVisible(true);
                                    String[] week = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
                                    for(Doctor d: mc.doctors){
                                        if((d.getName()+ " "+d.getSurName()).equals(docName)){
                                            for(int i = 0; i < 7; i++)
                                                setMeeting.add(new JLabel(week[i]));
                                            for(int i = 0; i < 7; i++)
                                                setMeeting.add(new JLabel(d.getTimeDay(i)));
                                        }
                                    }
                                }
                            });
                            meetingFrame.add(doctorSelectBTN);
                        }
                        JButton close = new JButton("Close");
                        close.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                meetingFrame.dispose();
                            }
                        });
                        meetingFrame.add(new JLabel());
                        meetingFrame.add(close);
                    }
                }
            });
            main.add(specBTN);
        }


        main.setLayout(mainGrid);


        loggedIn.add(info,BorderLayout.NORTH);
        loggedIn.add(main,BorderLayout.CENTER);
        loggedIn.add(buttons,BorderLayout.SOUTH);//
        //Panels ends there

        //info panel
        info.setLayout(new GridLayout(3,2));


//        time.setText("Time: " + (mc.dateFormat.format(mc.cal.getTime())));
//        time.setFont(new Font("Serif", Font.BOLD,24 ));
//        info.add(time);

        JLabel empty = new JLabel("");
        info.add(empty);

        JLabel name = new JLabel("You are logged in as: ");
        name.setFont(new Font("Serif", Font.BOLD, 20));
        name.setVerticalAlignment(SwingConstants.TOP);

        JLabel name1 = new JLabel(p.getName()+" "+p.getSurName());
        name1.setFont(new Font("Serif", Font.BOLD, 20));
        name1.setVerticalAlignment(SwingConstants.TOP);

        JLabel insuranceType = new JLabel("Your insurance is: ");
        insuranceType.setFont(new Font("Serif", Font.BOLD, 20));
        insuranceType.setVerticalAlignment(SwingConstants.TOP);

        JLabel insuranceType1 = new JLabel(p.getInsType());
        insuranceType1.setFont(new Font("Serif", Font.BOLD, 20));
        insuranceType1.setVerticalAlignment(SwingConstants.TOP);

        info.add(name);
        info.add(name1);
        info.add(insuranceType);
        info.add(insuranceType1);
        //Info labels ends there

        //buttuns panel
        buttons.setLayout(new GridLayout(0, 1));
        JButton signOutBTN = new JButton("Sign out");
        signOutBTN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPatientFrame();
                loggedIn.dispose();
            }
        });

        buttons.add(signOutBTN,BorderLayout.EAST);
        //Buttons ends there

        //loggedIn.pack();
        loggedIn.setVisible(true);

    }

    void mainPatientFrame() {

        JFrame frame = new JFrame("Medical Centre Application for Patients  ");
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 200);
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
                top.add (test,BorderLayout.WEST);

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
                top.add(regBTN,BorderLayout.EAST);

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


    public void tick(){
        time.setText("Time: " + (mc.dateFormat.format(mc.cal.getTime())));

    }
}