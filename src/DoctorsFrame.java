import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicArrowButton;


public class DoctorsFrame {
    private int currentDoc = 0; //TODO reikia kad reaguotu kuris daktaras!!!!

    MedicalCenter mc;
    Doctor d;
    JFrame frame;
    Methods methods = new Methods();
    DataMethods dataMethods = new DataMethods();

    JLabel timeDisp = new JLabel();
    JPanel panelSh = new JPanel();
    Calendar checkTime, scheduleTime;
    JTabbedPane tabs = new JTabbedPane();
    SimpleDateFormat weekFormat = new SimpleDateFormat("yyyy/MM/dd EE", Locale.ENGLISH);

    public DoctorsFrame(MedicalCenter mc, int currentDoc) {
        this.currentDoc = currentDoc;
        this.mc = mc;
    }

    public DoctorsFrame(){}

    public void init() {
        d = mc.doctors.get(currentDoc);
        frame = new JFrame("Medical Centre Application for Doctors - " + d.getName() + " " + d.getSurName());

        checkTime = (Calendar) mc.cal.clone();
        scheduleTime = (Calendar) mc.cal.clone();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(900, 200);
        frame.setSize(650, 600);
        frame.setResizable(false);
        frame.requestFocus();

        tabs.addTab("Doctors List", tabDoctors());
        tabs.addTab("Patients", tabPatients());
        tabs.addTab("Schedule", tabSchedule());

        tabs.setSelectedIndex(0);
        frame.add(tabs, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JLabel tabDoctors() {
        JPanel panelD = new JPanel();
        panelD.setLayout(new GridLayout(0, 1));

        for (Doctor d : mc.doctors) {
            JButton doctorBut = new JButton();
            doctorBut.setHorizontalAlignment(SwingConstants.LEFT);
            doctorBut.add(new JLabel(String.format("%1$" + 15 + "s", d.getName())));
            doctorBut.add(new JLabel(String.format("%1$" + 50 + "s", d.getSurName())));
            doctorBut.add(new JLabel(String.format("%1$" + 90 + "s", d.getCab())));
            doctorBut.add(new JLabel(String.format("%1$" + 130 + "s", d.getSpec())));
            doctorBut.setPreferredSize(new Dimension(300, 30));
            doctorBut.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Pressed on doctor: " + d.getName() + " " + d.getSurName());
                    doctorInfoFrame(d);
                }
            });
            panelD.add(doctorBut);
        }

        JScrollPane scrollPane = new JScrollPane(panelD);
        JLabel lab = new JLabel();
        lab.setLayout(new BorderLayout());
        lab.add(new JLabel(String.format("%1$" + 19 + "s", "Name")
                + String.format("%1$" + 29 + "s", "Surname")
                + String.format("%1$" + 30 + "s", "Cab")
                + String.format("%1$" + 36 + "s", "Specelizatoin")
        ), BorderLayout.NORTH);
        lab.add(scrollPane);

        return lab;
    }

    private JLabel tabPatients() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        for (Patient p : mc.patients) {
            JButton patientBut = new JButton();
            patientBut.setHorizontalAlignment(SwingConstants.LEFT);
            patientBut.add(new JLabel(p.getId()));
            patientBut.add(new JLabel(String.format("%1$" + 50 + "s", p.getName())));
            patientBut.add(new JLabel(String.format("%1$" + 90 + "s", p.getSurName())));
            patientBut.add(new JLabel(String.format("%1$" + 130 + "s", p.getInsType())));
            patientBut.setPreferredSize(new Dimension(300, 30));
            patientBut.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Pressed on patient: " + p.getName() + " " + p.getSurName());
                    methods.patientInfoFrame(p, mc);
                }
            });
            panel.add(patientBut);
        }

        JScrollPane scrollPaneP = new JScrollPane(panel);
        JLabel lab = new JLabel();
        lab.setLayout(new BorderLayout());
        lab.add(new JLabel(String.format("%1$" + 17 + "s", "ID") + String.format("%1$" + 36 + "s", "Name") + String.format("%1$" + 33 + "s", "Surname") + String.format("%1$" + 32 + "s", "Insurnace")), BorderLayout.NORTH);
        lab.add(scrollPaneP);
        return lab;
    }

    private JLabel tabPatients(Calendar date, Doctor doctor) {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        for (Patient p : mc.patients) {
            JButton patientBut = new JButton();
            patientBut.setHorizontalAlignment(SwingConstants.LEFT);
            patientBut.add(new JLabel(p.getId()));
            patientBut.add(new JLabel(String.format("%1$" + 50 + "s", p.getName())));
            patientBut.add(new JLabel(String.format("%1$" + 90 + "s", p.getSurName())));
            patientBut.add(new JLabel(String.format("%1$" + 130 + "s", p.getInsType())));
            patientBut.setPreferredSize(new Dimension(300, 30));
            patientBut.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("added patient: " + p.getName() + " " + p.getSurName());
                    addInfoF.dispose();
                    String dateString = doctor.getId() + "_" + date.get(Calendar.YEAR) + "-" + String.format("%1$" + 2 + "s", date.get(Calendar.MONTH) + 1).replace(" ", "0") + "-" + String.format("%1$" + 2 + "s", date.get(Calendar.DAY_OF_MONTH)).replace(" ", "0")
                            + "_" + String.format("%1$" + 2 + "s", date.get(Calendar.HOUR_OF_DAY)).replace(" ", "0") + ":" + String.format("%1$" + 2 + "s", date.get(Calendar.MINUTE)).replace(" ", "0");
                    p.setAppointments(dateString);
                    checkTime.add(Calendar.YEAR, 1);
//////////////////////////////
                    dataMethods.updateDatesFile(mc.patients);
//////////////////////////////
                }
            });
            panel.add(patientBut);
        }
        JScrollPane scrollPaneP = new JScrollPane(panel);
        JLabel lab = new JLabel();
        lab.setLayout(new BorderLayout());
        lab.add(new JLabel(String.format("%1$" + 17 + "s", "ID") + String.format("%1$" + 36 + "s", "Name") + String.format("%1$" + 33 + "s", "Surname") + String.format("%1$" + 32 + "s", "Insurnace")), BorderLayout.NORTH);
        lab.add(scrollPaneP);
        return lab;
    }

    private JLabel tabSchedule() {

        panelSh.setLayout(new GridLayout(0, 2, 30, 30));
        for (int i = 1; i < 7; i++) {
            panelSh.add(dayPanel(i));
        }
        panelSh.setPreferredSize(new Dimension(0, 1111));
        JScrollPane scrollPaneP = new JScrollPane(panelSh);

        JLabel lab = new JLabel();
        lab.setLayout(new BorderLayout());

        JPanel jPan = new JPanel();
        JLabel timeLabel = new JLabel(weekFormat.format(scheduleTime.getTime()));
        jPan.add(timeLabel);
        BasicArrowButton arrowWest = new BasicArrowButton(BasicArrowButton.WEST);
        arrowWest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scheduleTime.add(Calendar.WEEK_OF_YEAR, -1);
                timeLabel.setText(weekFormat.format(scheduleTime.getTime()));
                checkTime.add(Calendar.YEAR, 1);
            }
        });
        BasicArrowButton arrowEast = new BasicArrowButton(BasicArrowButton.EAST);
        arrowEast.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scheduleTime.add(Calendar.WEEK_OF_YEAR, 1);
                timeLabel.setText(weekFormat.format(scheduleTime.getTime()));
                checkTime.add(Calendar.YEAR, 1);
            }
        });
        jPan.add(arrowWest);
        jPan.add(arrowEast);
        lab.add(jPan, BorderLayout.NORTH);
        lab.add(scrollPaneP, BorderLayout.CENTER);

        return lab;
    }

    public JPanel dayPanel(int day) {
        Calendar time;
        time = (Calendar) scheduleTime.clone();
        time.set(Calendar.DAY_OF_WEEK, day + 1);
        day -= 1;
        JPanel dayPanel = new JPanel(new BorderLayout());
        SimpleDateFormat dayNameFormat = new SimpleDateFormat("EEEE (dd'd.') ");
        DateFormat dateForm = new SimpleDateFormat("HH:mm ");
        if (!(d.getTimeDay(day).equals("-"))) {
            dayPanel.add(new JLabel(String.format("%1$-" + 37 + "s", dayNameFormat.format(time.getTime())) + String.format("%1$" + 11 + "s", d.getTimeDay(day)).replace(" ", "0")), BorderLayout.NORTH); // dienos pavadinimas ir darbo laikas ta diena

            { // Dienos susitikimu laikai  + pacentas + mygtukai
                String[] s = (d.getTimeDay(day).split("-|:"));
                time.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s[0]));
                time.set(Calendar.MINUTE, Integer.parseInt(s[1]));
                JPanel pan = new JPanel(new GridLayout(0, 1, 3, 3));
                JPanel pan2 = new JPanel(new GridLayout(0, 1, 3, 3));
                pan.setPreferredSize(new Dimension(90, 40));
                pan2.setPreferredSize(new Dimension(200, 40));

                Calendar temptime = (Calendar) time.clone();
                temptime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s[0]));
                temptime.set(Calendar.MINUTE, Integer.parseInt(s[1]));
                Calendar temptime2 = (Calendar) temptime.clone();
                temptime2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s[2]));
                temptime2.set(Calendar.MINUTE, Integer.parseInt(s[3]));

                for (Calendar cal = temptime; cal.before(temptime2); cal.add(Calendar.MINUTE, 30)) {
                    JLabel labTemp = new JLabel(dateForm.format(time.getTime()) + " - ");
                    time.add(Calendar.MINUTE, 30);
                    labTemp.setText(labTemp.getText() + dateForm.format(time.getTime()));
                    pan.add(labTemp);
                    pan2.add(patientBut(cal, mc.doctors.get(currentDoc)), BorderLayout.CENTER); // pacentas ir mygtukai
                }

                dayPanel.add(pan, BorderLayout.WEST);
                dayPanel.add(pan2, BorderLayout.EAST);
            }
            return dayPanel;
        } else {
            dayPanel.add(new JLabel(String.format("%1$-" + 12 + "s", dayNameFormat.format(time.getTime())) + "   ----"), BorderLayout.NORTH);
            dayPanel.add(new JLabel("Chilling at home"));
            return dayPanel;
        }
    }

    private JPanel patientBut(Calendar time, Doctor doctor) {
        JPanel timeLine = new JPanel(new BorderLayout(1, 1));
        JButton but = new JButton("");
        JPanel butPan = new JPanel(new GridBagLayout());
        butPan.add(but);
        for (Patient p : mc.patients) {
            if (p.getDocId() != null) {
                for (int j = 0; j < p.getDocId().size(); j++) {
                    if (Integer.parseInt(p.getDocId().get(j)) == doctor.getId()) {
                        Calendar tempCal = (Calendar) p.getTime().get(j).clone();
                        if (time.get(Calendar.YEAR) == tempCal.get(Calendar.YEAR)
                                && time.get(Calendar.DAY_OF_YEAR) == tempCal.get(Calendar.DAY_OF_YEAR)
                                && time.get(Calendar.HOUR_OF_DAY) == tempCal.get(Calendar.HOUR_OF_DAY)
                                && time.get(Calendar.MINUTE) == tempCal.get(Calendar.MINUTE)
                                ) {
                            but.setText("i");
                            but.setBorder(BorderFactory.createLineBorder(null));
                            but.setPreferredSize(new Dimension(21, 19));
                            timeLine.add(new JLabel(String.format("%1$" + 25 + "s", p.getName() + " " + p.getSurName())), BorderLayout.CENTER);
                            timeLine.add(butPan, BorderLayout.WEST);
                            but.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    System.out.println("Cheking patient: " + p.getName() + " " + p.getSurName());
                                    methods.patientInfoFrame(p, mc);
                                }
                            });
                            JPanel but2Pan = new JPanel(new GridBagLayout());
                            JButton but2 = new JButton("x");
                            if (time.getTime().before(mc.cal.getTime())) {
                                but2.setEnabled(false);
                            }
                            but2Pan.add(but2);
                            but2.setBorder(BorderFactory.createLineBorder(null));
                            but2.setPreferredSize(new Dimension(21, 19));
                            int a = j;
                            but2.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (but2.isEnabled()) {
                                        JDialog cancelApointment = new JDialog(frame, "Remove", Dialog.ModalityType.DOCUMENT_MODAL);
                                        cancelApointment.setLayout(new GridLayout(2, 1));
                                        cancelApointment.setLocation(frame.getX() + 50, frame.getY() + 100);
                                        cancelApointment.setSize(300, 100);
                                        cancelApointment.setResizable(false);
                                        JButton yesBut = new JButton("YES");
                                        yesBut.addMouseListener(new MouseAdapter() {
                                            @Override
                                            public void mouseClicked(MouseEvent e) {
                                                p.getAppointments().remove(a);
                                                p.AppointmentToTime();
                                                checkTime.add(Calendar.YEAR, 1);
                                                dataMethods.updateDatesFile(mc.patients);
                                                cancelApointment.dispose();
                                            }
                                        });
                                        JButton noBut = new JButton("NO");
                                        noBut.addMouseListener(new MouseAdapter() {
                                            @Override
                                            public void mouseClicked(MouseEvent e) {
                                                cancelApointment.dispose();
                                            }
                                        });

                                        cancelApointment.add(new JLabel("Remove appointment?", SwingConstants.CENTER));
                                        JPanel jPan = new JPanel(new GridBagLayout());
                                        jPan.add(yesBut);
                                        jPan.add(new JLabel(" "));
                                        jPan.add(noBut);
                                        cancelApointment.add(jPan);
                                        cancelApointment.setVisible(true);

                                    }
                                }
                            });
                            timeLine.add(but2Pan, BorderLayout.EAST);
                        }
                    }
                }

            }
        }
        if (but.getText().equals("")) {
            but.setText("+");
            if (time.getTime().before(mc.cal.getTime())) {
                but.setEnabled(false);
            }
            but.setBorder(BorderFactory.createLineBorder(null));
            but.setPreferredSize(new Dimension(21, 19));
            Calendar timeClone = (Calendar) time.clone();
            timeLine.add(new JLabel(String.format("%1$" + 20 + "s", "----")), BorderLayout.CENTER);
            timeLine.add(butPan, BorderLayout.EAST);
            but.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (but.isEnabled()) {
                        System.out.println("Setting up new appointment " + timeClone.getTime());
                        but.setText(addPatientAppointment(timeClone, doctor));
                    }
                }
            });
        }
        return timeLine;
    }


    public void doctorInfoFrame(Doctor d) {
        JFrame infoF = new JFrame(d.getName() + " " + d.getSurName());
        infoF.setSize(500, 650);
        infoF.setLocation(100, 160);
        infoF.setLocationByPlatform(true);
        infoF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel danel = new JPanel();

        JPanel info = new JPanel();
        info.add(new JLabel("Doctor: "));
        JTextField txt = new JTextField(d.getName().toUpperCase() + " " + d.getSurName().toUpperCase(), 15);
        txt.setEditable(false);
        info.add(txt);
        info.add(new JLabel("  Cab: "));
        JTextField txt1 = new JTextField(Integer.toString(d.getCab()), 8);
        txt1.setEditable(false);
        info.add(txt1);

        /*JPanel info3 = new JPanel();
        info3.setLayout(new BorderLayout(10, 0));
        JTextArea txt3 = new JTextArea("", 14, 20);
        txt3.setLineWrap(true);
        txt3.setWrapStyleWord(true);
        info3.add(txt3, BorderLayout.CENTER);
        info3.add(new JLabel(), BorderLayout.PAGE_END);
        txt3.setEditable(false);*/

        danel.setLayout(new BorderLayout(10, 10));
        JPanel info0 = new JPanel();
        info0.setLayout(new GridLayout(0, 1));
        info0.add(info);
        danel.add(info0, BorderLayout.BEFORE_FIRST_LINE);

        danel.add(methods.selectTimePanel(d, null, mc.cal, mc), BorderLayout.CENTER);
        //danel.add(info3, BorderLayout.CENTER);
        danel.add(new JLabel(""), BorderLayout.BEFORE_LINE_BEGINS);
        danel.add(new JLabel(" "), BorderLayout.AFTER_LINE_ENDS);
        danel.add(new JLabel(), BorderLayout.PAGE_END);

        infoF.add(danel);
        infoF.setVisible(true);
    }

    JDialog addInfoF;

    public String addPatientAppointment(Calendar time, Doctor doctor) {
        addInfoF = new JDialog(frame, "Set appointment", Dialog.ModalityType.DOCUMENT_MODAL);
        addInfoF.setSize(500, 500);
        addInfoF.add(tabPatients(time, doctor));
        addInfoF.setLocation(100, 150);
        addInfoF.setLocationByPlatform(true);
        addInfoF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addInfoF.setVisible(true);

        return "add";
    }

    public void tick() {
        timeDisp.setText("Time: " + (weekFormat.format(mc.cal.getTime())));
        if (checkTime.get(Calendar.DAY_OF_YEAR) != mc.cal.get(Calendar.DAY_OF_YEAR) || checkTime.get(Calendar.YEAR) != mc.cal.get(Calendar.YEAR)) {
            System.out.println("Refreshed");
            if (checkTime.before(mc.cal))
                scheduleTime.setTime(mc.cal.getTime());
            int indexOfTab = tabs.getSelectedIndex();
            panelSh.removeAll();
            tabs.add("Schedule", tabSchedule());
            tabs.remove(2);
            tabs.setSelectedIndex(indexOfTab);
            checkTime = (Calendar) mc.cal.clone();
        }
    }
}
