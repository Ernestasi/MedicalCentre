import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;


public class DoctorsFrame{
    int currentDoctor = 0; //TODO reikia kad reaguotu kuris daktaras!!!!

    MedicalCenter mc;
    Doctor d;
    JFrame frame;

    JLabel timeDisp = new JLabel();
    JPanel panelSh = new JPanel();
    Calendar checkTime;
    Calendar scheduleTime;
    JTabbedPane tabs = new JTabbedPane();
    SimpleDateFormat weekFormat = new SimpleDateFormat("yyyy/MM/dd EE", Locale.ENGLISH);

    public DoctorsFrame(MedicalCenter mc){
        this.mc = mc;
    }

    public void init(){
        d = mc.doctors.get(currentDoctor);
        frame = new JFrame("Medical Centre Application for Doctors  ");

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

    private JLabel tabDoctors(){
        JPanel panelD = new JPanel();
        panelD.setLayout(new GridLayout(0, 1));

        for(Doctor d : mc.doctors){
            JButton but = new JButton();
            but.setHorizontalAlignment(SwingConstants.LEFT);
            but.add(new JLabel(String.format("%1$" + 15 + "s", d.getName())));
            but.add(new JLabel(String.format("%1$" + 50 + "s", d.getSurName())));
            but.add(new JLabel(String.format("%1$" + 90 + "s", d.getCab())));
            but.add(new JLabel(String.format("%1$" + 130 + "s", d.getSpec())));
            but.setPreferredSize(new Dimension(300, 30));
            but.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    System.out.println("Pressed on doctor: " + d.getName() + " " + d.getSurName());
                    doctorInfoFrame(d);
                }
            });
            panelD.add(but);
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

    private JLabel tabPatients(){

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        for(Patient p : mc.patients){
            JButton but = new JButton();
            but.setHorizontalAlignment(SwingConstants.LEFT);
            but.add(new JLabel(p.getId()));
            but.add(new JLabel(String.format("%1$" + 50 + "s", p.getName())));
            but.add(new JLabel(String.format("%1$" + 90 + "s", p.getSurName())));
            but.add(new JLabel(String.format("%1$" + 130 + "s", p.getInsType())));
            but.setPreferredSize(new Dimension(300, 30));
            but.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    System.out.println("Pressed on patient: " + p.getName() + " " + p.getSurName());
                    patientInfoFrame(p);
                }
            });
            panel.add(but);
        }

        JScrollPane scrollPaneP = new JScrollPane(panel);
        JLabel lab = new JLabel();
        lab.setLayout(new BorderLayout());
        lab.add(new JLabel(
                String.format("%1$" + 17 + "s", "ID")
                        + String.format("%1$" + 36 + "s", "Name")
                        + String.format("%1$" + 33 + "s", "Surname")
                        + String.format("%1$" + 32 + "s", "Insurnace")
        ), BorderLayout.NORTH);
        lab.add(scrollPaneP);
        return lab;
    }

    private JLabel tabPatients(Calendar date){

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        for(Patient p : mc.patients){
            JButton but = new JButton();
            but.setHorizontalAlignment(SwingConstants.LEFT);
            but.add(new JLabel(p.getId()));
            but.add(new JLabel(String.format("%1$" + 50 + "s", p.getName())));
            but.add(new JLabel(String.format("%1$" + 90 + "s", p.getSurName())));
            but.add(new JLabel(String.format("%1$" + 130 + "s", p.getInsType())));
            but.setPreferredSize(new Dimension(300, 30));
            but.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    System.out.println("added patient: " + p.getName() + " " + p.getSurName());
                    addInfoF.dispose();
                    String dateString = currentDoctor + "_" + date.get(Calendar.YEAR) + "-" + String.format("%1$" + 2 + "s", date.get(Calendar.MONTH) + 1).replace(" ", "0") + "-" + String.format("%1$" + 2 + "s", date.get(Calendar.DAY_OF_MONTH)).replace(" ", "0")
                            + "_" + String.format("%1$" + 2 + "s", date.get(Calendar.HOUR_OF_DAY)).replace(" ", "0") + ":" + String.format("%1$" + 2 + "s", date.get(Calendar.MINUTE)).replace(" ", "0");
                    p.setAppointments(dateString);
                    System.out.println(p.getAppointments().toString());
                    checkTime.add(Calendar.YEAR, 1);
                      /*  try{
                            BufferedReader br = new BufferedReader(new FileReader("./src/Data/Dates.txt"));
                            String line;
                            while((line = br.readLine()) != null){
                                String[] a = line.split(",");
                                if(a[0].equals(p.getId())){
                                    line = line + "0_2017-04-19_10:00";
                                }
                            }
                            br.close();

                        }catch(IOException ee){
                            ee.printStackTrace();
                        }*/
                    //todo setting up patient's appointment
                }
            });
            panel.add(but);
        }
        JScrollPane scrollPaneP = new JScrollPane(panel);
        JLabel lab = new JLabel();
        lab.setLayout(new BorderLayout());
        lab.add(new JLabel(
                String.format("%1$" + 17 + "s", "ID")
                        + String.format("%1$" + 36 + "s", "Name")
                        + String.format("%1$" + 33 + "s", "Surname")
                        + String.format("%1$" + 32 + "s", "Insurnace")
        ), BorderLayout.NORTH);
        lab.add(scrollPaneP);
        return lab;
    }

    private JLabel tabSchedule(){

        panelSh.setLayout(new GridLayout(0, 2, 30, 30));
        panelSh.setPreferredSize(new Dimension(0, 1000));
        panelSh.add(timeGraph(1));
        panelSh.add(timeGraph(2));
        panelSh.add(timeGraph(3));
        panelSh.add(timeGraph(4));
        panelSh.add(timeGraph(5));
        panelSh.add(timeGraph(6));

        JScrollPane scrollPaneP = new JScrollPane(panelSh);

        JLabel lab = new JLabel();
        lab.setLayout(new BorderLayout());

        JPanel jPan = new JPanel();
        JLabel timeLabel = new JLabel(weekFormat.format(scheduleTime.getTime()));
        jPan.add(timeLabel);
        BasicArrowButton arrowWest = new BasicArrowButton(BasicArrowButton.WEST);
        arrowWest.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                scheduleTime.add(Calendar.WEEK_OF_YEAR, -1);
                timeLabel.setText(weekFormat.format(scheduleTime.getTime()));
                checkTime.add(Calendar.YEAR, 1);
            }
        });
        BasicArrowButton arrowEast = new BasicArrowButton(BasicArrowButton.EAST);
        arrowEast.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
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

    private JPanel timeGraph(int day){
        Calendar time;
        time = (Calendar) scheduleTime.clone();
        time.set(Calendar.DAY_OF_WEEK, day + 1);
        String dayName;
        if(day == 1){
            dayName = "Monday (" + time.get(Calendar.DAY_OF_MONTH ) + "d) :  ";
        }else if(day == 2){
            dayName = "Tuesday (" + time.get(Calendar.DAY_OF_MONTH ) + "d) :  ";
        }else if(day == 3){
            dayName = "Wednesday (" + time.get(Calendar.DAY_OF_MONTH ) + "d) :  ";
        }else if(day == 4){
            dayName = "Thursday (" + time.get(Calendar.DAY_OF_MONTH ) + "d) :  ";
        }else if(day == 5){
            dayName = "Friday (" + time.get(Calendar.DAY_OF_MONTH ) + "d) :  ";
        }else if(day == 6){
            dayName = "Saturday (" + time.get(Calendar.DAY_OF_MONTH ) + "d) :  ";
        }else{
            dayName = "Sunday (" + time.get(Calendar.DAY_OF_MONTH ) + "d) :  ";
        }
        day -= 1;
        JPanel dayPanel = new JPanel(new BorderLayout());
        dayPanel.setPreferredSize(new Dimension(300, 300));
        dayPanel.setMaximumSize(new Dimension(300, 300));

        DateFormat dateForm = new SimpleDateFormat("HH:mm ");
        if(!(d.getTimeDay(day).equals("-"))){
            dayPanel.add(new JLabel(String.format("%1$-" + 37 + "s", dayName) + String.format("%1$" + 11 + "s", d.getTimeDay(day)).replace(" ", "0")), BorderLayout.NORTH);

            {
                String[] s = (d.getTimeDay(day).split("-|:"));
                time.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s[0]));
                time.set(Calendar.MINUTE, Integer.parseInt(s[1]));
                double time0 = Integer.parseInt(s[0]) + Double.parseDouble(s[1]) / 60;
                double time1 = Integer.parseInt(s[2]) + Double.parseDouble(s[3]) / 60;
                JPanel pan = new JPanel();
                pan.setLayout(new GridLayout(0, 1));
                JPanel pan2 = new JPanel();
                pan2.setLayout(new GridLayout(0, 1));
                pan.setPreferredSize(new Dimension(90, 20));
                pan.setMaximumSize(new Dimension(90, 20));
                pan2.setPreferredSize(new Dimension(200, 20));
                pan2.setMaximumSize(new Dimension(200, 20));

                Calendar temptime = (Calendar) time.clone();

                temptime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s[0]));
                temptime.set(Calendar.MINUTE, Integer.parseInt(s[1]));
                Calendar temptime2 = (Calendar) temptime.clone();
                temptime2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s[2]));
                temptime2.set(Calendar.MINUTE, Integer.parseInt(s[3]));
                for(Calendar cal = temptime; cal.before(temptime2); cal.add(Calendar.MINUTE, 30)){
                    JLabel labTemp = new JLabel(dateForm.format(time.getTime()) + " - ");
                    time.add(Calendar.MINUTE, 30);
                    labTemp.setText(labTemp.getText() + dateForm.format(time.getTime()));
                    pan.add(labTemp);
                    pan2.add(patientBut(cal), BorderLayout.CENTER);
                }
                dayPanel.add(pan, BorderLayout.WEST);
                dayPanel.add(pan2, BorderLayout.EAST);
            }

            return dayPanel;
        }else{
            dayPanel.add(new JLabel(String.format("%1$-" + 12 + "s", dayName) + d.getTimeDay(day)), BorderLayout.NORTH);
            dayPanel.add(new JLabel("Chilling at home"));
            return dayPanel;
        }
    }

    private JPanel patientBut(Calendar time){
        JPanel timeLine = new JPanel(new BorderLayout(1 ,1));
        JButton but = new JButton("");
        for(Patient p : mc.patients){
            if(p.getDocId() != null){
                for(int j = 0; j < p.getDocId().size(); j++){
                    String tempS = p.getDocId().get(j);
                    Calendar tempCal = (Calendar) p.getTime().get(j).clone();
                    if(Integer.parseInt(tempS) == currentDoctor){
                        if(time.get(Calendar.YEAR) == tempCal.get(Calendar.YEAR)
                                && time.get(Calendar.MONTH) == tempCal.get(Calendar.MONTH)
                                && time.get(Calendar.DAY_OF_MONTH) == tempCal.get(Calendar.DAY_OF_MONTH)
                                && time.get(Calendar.HOUR_OF_DAY) == tempCal.get(Calendar.HOUR_OF_DAY)
                                && time.get(Calendar.MINUTE) == tempCal.get(Calendar.MINUTE)
                                ){
                            but.setText("i");
                            but.setBorder(BorderFactory.createEmptyBorder());
                            but.setPreferredSize(new Dimension(20, 10));
                            timeLine.add(new JLabel(String.format("%1$" + 25 + "s", p.getName() + " " + p.getSurName())), BorderLayout.CENTER);
                            timeLine.add(but, BorderLayout.WEST);
                            but.addMouseListener(new MouseAdapter(){
                                @Override
                                public void mouseClicked(MouseEvent e){
                                    System.out.println("Cheking patient: " + p.getName() + " " + p.getSurName());
                                    patientInfoFrame(p);
                                }
                            });
                            JButton but2 = new JButton("x");
                            but2.setBorder(BorderFactory.createLineBorder(null));
                            but2.setPreferredSize(new Dimension(30, 10));
                            int a= j;
                            but2.addMouseListener(new MouseAdapter(){
                                @Override
                                public void mouseClicked(MouseEvent e){
                                    JDialog cancelApointment = new JDialog(frame, "Remove", Dialog.ModalityType.DOCUMENT_MODAL);
                                    cancelApointment.setLayout(new GridLayout(2, 1));
                                    cancelApointment.setLocation(frame.getX()+50, frame.getY()+100);
                                    cancelApointment.setSize(300, 100);
                                    JButton yesBut = new JButton("YES");
                                    yesBut.addMouseListener(new MouseAdapter(){
                                        @Override
                                        public void mouseClicked(MouseEvent e){
                                            p.getAppointments().remove(a);
                                            p.AppointmentToTime();
                                            checkTime.add(Calendar.YEAR, 1);
                                            cancelApointment.dispose();
                                        }
                                    });
                                    JButton noBut = new JButton("NO");
                                    noBut.addMouseListener(new MouseAdapter(){
                                        @Override
                                        public void mouseClicked(MouseEvent e){
                                            cancelApointment.dispose();
                                        }
                                    });

                                    cancelApointment.add(new JLabel("Remove appointment?", SwingConstants.CENTER));
                                    JPanel jPan =new JPanel(new GridBagLayout());
                                    jPan.add(yesBut);
                                    jPan.add(new JLabel(" "));
                                    jPan.add(noBut);
                                    cancelApointment.add(jPan);
                                    cancelApointment.setVisible(true);


                                }
                            });
                            timeLine.add(but2, BorderLayout.EAST);
                        }
                    }
                }

            }
        }
        if(but.getText().equals("")){
            but.setText("+");
            but.setBorder(BorderFactory.createLineBorder(null));
            but.setPreferredSize(new Dimension(30, 10));
            Calendar timeClone = (Calendar) time.clone();
            timeLine.add(new JLabel(String.format("%1$" + 20 + "s", "----")), BorderLayout.CENTER);
            timeLine.add(but, BorderLayout.EAST);
            but.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    System.out.println("Setting up new appointment " + timeClone.getTime());

                    but.setText(addPatientAppointment(timeClone));
                }
            });
        }
        return timeLine;
    }

    private void patientInfoFrame(Patient p){
        JFrame infoF = new JFrame(p.getName() + " " + p.getSurName());
        infoF.setSize(400, 500);
        infoF.setLocation(200, 160);
        infoF.setLocationByPlatform(true);
        infoF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        JPanel info = new JPanel();
        info.add(new JLabel("Patient: "));
        JTextField txt = new JTextField(p.getName().toUpperCase() + " " + p.getSurName().toUpperCase(), 15);
        txt.setEditable(false);
        info.add(txt);
        info.add(new JLabel("  ID: "));
        JTextField txt1 = new JTextField(p.getId(), 8);
        txt1.setEditable(false);
        info.add(txt1);

        JPanel info2 = new JPanel();
        info2.add(new JLabel("Insurance: "));
        JTextField txt2 = new JTextField(p.getInsType(), 13);
        info2.add(txt2);
        info2.add(new JLabel("    Description:            "));
        txt2.setEditable(false);

        JPanel info3 = new JPanel();
        info3.setLayout(new BorderLayout(10, 0));
        JTextArea txt3 = new JTextArea(p.getDescription(), 14, 20);//todo description text
        txt3.setLineWrap(true);
        txt3.setWrapStyleWord(true);
        info3.add(txt3, BorderLayout.CENTER);
        txt3.setEditable(false);
        JButton editBut = new JButton( " Edit ");
        JButton closeBut = new JButton(" Close");

        final String[] tempText = {txt3.getText()};
        editBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(!txt3.isEditable()){
                    txt3.setEditable(true);
                    editBut.setText(" Save  ");
                    closeBut.setText("Cancel");
                    if(!txt3.getText().equals(" "))
                        txt3.setText(tempText[0] + "\n");
                    txt3.requestFocus();
                }else{
                    txt3.setEditable(false);
                    tempText[0] = txt3.getText();
                    editBut.setText(" Edit ");
                    closeBut.setText(" Close");
                    p.setDescription(txt3.getText());
                }
            }
        });
        closeBut.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if(closeBut.getText().equals(" Close")){
                    infoF.dispose();
                }else{
                    txt3.setEditable(false);
                    editBut.setText(" Edit ");
                    txt3.setText(tempText[0]);
                }
            }
        });
        JPanel jl = new JPanel();
        jl.setLayout(new GridBagLayout());
        jl.add(editBut);
        jl.add(closeBut);

        info3.add(jl, BorderLayout.PAGE_END);
        panel.setLayout(new BorderLayout(10, 10));
        JPanel info0 = new JPanel();
        info0.setLayout(new GridLayout(0, 1));
        info0.add(info);
        info0.add(info2);
        panel.add(info0, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(info3, BorderLayout.CENTER);
        panel.add(new JLabel(" "), BorderLayout.BEFORE_LINE_BEGINS);
        // panel.add(editBut, BorderLayout.SOUTH);
        panel.add(new JLabel(" "), BorderLayout.AFTER_LINE_ENDS);
        panel.add(new JLabel(), BorderLayout.PAGE_END);

        infoF.add(panel);
        infoF.setVisible(true);
    }

    private void doctorInfoFrame(Doctor d){
        JFrame infoF = new JFrame(d.getName() + " " + d.getSurName());
        infoF.setSize(400, 500);
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


        JPanel info3 = new JPanel();
        info3.setLayout(new BorderLayout(10, 0));
        JTextArea txt3 = new JTextArea("", 14, 20);
        txt3.setLineWrap(true);
        txt3.setWrapStyleWord(true);
        info3.add(txt3, BorderLayout.CENTER);
        info3.add(new JLabel(), BorderLayout.PAGE_END);
        txt3.setEditable(false);

        danel.setLayout(new BorderLayout(10, 10));
        JPanel info0 = new JPanel();
        info0.setLayout(new GridLayout(0, 1));
        info0.add(info);
        danel.add(info0, BorderLayout.BEFORE_FIRST_LINE);
        danel.add(info3, BorderLayout.CENTER);
        danel.add(new JLabel(""), BorderLayout.BEFORE_LINE_BEGINS);
        danel.add(new JLabel(" "), BorderLayout.AFTER_LINE_ENDS);
        danel.add(new JLabel(), BorderLayout.PAGE_END);

        infoF.add(danel);
        infoF.setVisible(true);
    }

    JDialog addInfoF;

    private String addPatientAppointment(Calendar time){
        addInfoF = new JDialog(frame, "Set appointment", Dialog.ModalityType.DOCUMENT_MODAL);
        addInfoF.setSize(500, 500);
        addInfoF.add(tabPatients(time));
        addInfoF.setLocation(100, 150);
        addInfoF.setLocationByPlatform(true);
        addInfoF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addInfoF.setVisible(true);

        return "add";
    }

    public void tick(){
        timeDisp.setText("Time: " + (weekFormat.format(mc.cal.getTime())));
        if(checkTime.get(Calendar.DAY_OF_YEAR) != mc.cal.get(Calendar.DAY_OF_YEAR)|| checkTime.get(Calendar.YEAR) != mc.cal.get(Calendar.YEAR)){
            System.out.println("Refreshed");
            if(checkTime.before(mc.cal))
                scheduleTime.setTime(mc.cal.getTime());
            int indexOfTab = tabs.getSelectedIndex();
            panelSh.removeAll();
            tabs.add("Schedule", tabSchedule());
            tabs.remove(2);
            tabs.setSelectedIndex(indexOfTab);
            // aaa=10;
            checkTime = (Calendar) mc.cal.clone();
        }
        frame.repaint();

        // System.out.println(mc.cal.get(Calendar.DAY_OF_WEEK)); // TODO will need this ;)
    }
}
