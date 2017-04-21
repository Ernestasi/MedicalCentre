import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;


public class DoctorsFrame{
    int currentDoctor=0; //TODO reikia kad reaguotu kuris daktaras!!!!

    MedicalCenter mc;
    Doctor d;
    JFrame frame;

    JLabel timeDisp = new JLabel();
    JPanel panelSh = new JPanel();
    int checkTime;
    JTabbedPane tabs = new JTabbedPane();

    public DoctorsFrame(MedicalCenter mc){
        this.mc = mc;
    }

    public void init(){
        d = mc.doctors.get(currentDoctor);
        frame = new JFrame("Medical Centre Application for Doctors  ");

        checkTime = mc.cal.get(Calendar.DAY_OF_YEAR);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(900, 200);
        frame.setSize(650, 600);
        frame.setResizable(false);
        frame.requestFocus();

        tabs.addTab("Doctors List", tabDoctors());
        tabs.addTab("Patients", tabPatients(0));
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
            but.add(new JLabel(String.format("%1$" + 130 + "s", "Specelizatoin")));
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

    private JLabel tabPatients(int a){

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
                    if(a == 0){
                        System.out.println("Pressed on patient: " + p.getName() + " " + p.getSurName());
                        patientInfoFrame(p);
                    }else if(a == 1){
                        System.out.println("added patient: " + p.getName() + " " + p.getSurName());
                        addInfoF.dispose();
                        //todo setting up patient's appointment
                    }
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
        lab.add(timeDisp, BorderLayout.NORTH);
        lab.add(scrollPaneP);


        return lab;
    }

    private JLabel timeGraph(int day){
        Calendar time;
        time = (Calendar) mc.cal.clone();
        time.set(mc.cal.DAY_OF_WEEK, day + 1);
        String name;
        if(day == 1){
            name = "Monday: ";
        }else if(day == 2){
            name = "Tuesday: ";
        }else if(day == 3){
            name = "Wednesday: ";
        }else if(day == 4){
            name = "Thursday: ";
        }else if(day == 5){
            name = "Friday: ";
        }else if(day == 6){
            name = "Saturday: ";
        }else{
            name = "Sunday: ";
        }
        day -= 1;
        JLabel temp = new JLabel();

        DateFormat dateForm = new SimpleDateFormat("HH:mm ");
        temp.setLayout(new BorderLayout());
        if(!(d.getTimeDay(day).equals("-"))){
            temp.add(new JLabel(String.format("%1$-" + 37 + "s", name) + String.format("%1$" + 11 + "s", d.getTimeDay(day)).replace(" ", "0")), BorderLayout.NORTH);

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
                Calendar temptime = (Calendar)time.clone();

                temptime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s[0]));
                temptime.set(Calendar.MINUTE, Integer.parseInt(s[1]));
                Calendar temptime2 = (Calendar)temptime.clone();
                temptime2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s[2]));
                temptime2.set(Calendar.MINUTE, Integer.parseInt(s[3]));
                for(Calendar c = temptime; c.before(temptime2); c.add(Calendar.MINUTE, 30)){
                    JLabel labTemp = new JLabel(dateForm.format(time.getTime()) + " - ");
                    time.add(Calendar.MINUTE, 30);
                    labTemp.setText(labTemp.getText() + dateForm.format(time.getTime()));
                    pan.add(labTemp);
                    pan2.add(patientBut(c), BorderLayout.CENTER);
                }
                temp.add(pan, BorderLayout.WEST);
                temp.add(pan2, BorderLayout.EAST);
            }

            return temp;
        }else{
            temp.add(new JLabel(String.format("%1$-" + 12 + "s", name) + d.getTimeDay(day)), BorderLayout.NORTH);
            temp.add(new JLabel("Chilling at home"));
            return temp;
        }
    }

    private JPanel patientBut(Calendar time){
        JPanel jb = new JPanel();
        jb.setLayout(new BorderLayout());
        JButton but = new JButton("");
        for(Patient p : mc.patients){
            if(p.getDocId() != null){
                for(int j = 0; j<p.getDocId().size(); j++){
                   String tempS = p.getDocId().get(j);
                   Calendar tempCal = (Calendar) p.getTime().get(j).clone();
                    if(Integer.parseInt(tempS) == currentDoctor){
                        if( time.get(Calendar.YEAR) == tempCal.get(Calendar.YEAR)
                                && time.get(Calendar.MONTH) == tempCal.get(Calendar.MONTH)
                                && time.get(Calendar.DAY_OF_MONTH) == tempCal.get(Calendar.DAY_OF_MONTH)
                                && time.get(Calendar.HOUR_OF_DAY) == tempCal.get(Calendar.HOUR_OF_DAY)
                                && time.get(Calendar.MINUTE) == tempCal.get(Calendar.MINUTE)
                            ){
                            System.out.println("#" + mc.dateFormat.format(time.getTime()));
                            System.out.println("     " + mc.dateFormat.format(tempCal.getTime()));
                            but.setText("⌂");
                            jb.add(new JLabel(p.getName() + " " + p.getSurName()), BorderLayout.WEST);
                            jb.add(but, BorderLayout.EAST);
                            but.addMouseListener(new MouseAdapter(){
                                @Override
                                public void mouseClicked(MouseEvent e){
                                    System.out.println("Cheking patient: " + p.getName() + " " + p.getSurName());
                                    patientInfoFrame(p);
                                }
                            });
                        }
                    }
                }

            }
        }
        if(but.getText().equals("")){
            but.setText("add");
            jb.add(new JLabel(String.format("%1$-" + 20 + "s", "----")), BorderLayout.WEST);
            jb.add(but, BorderLayout.EAST);
            but.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    System.out.println("Setting up new appointment");

                    but.setText(addPatientAppointment(time));
                }
            });
        }
        return jb;
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
        info2.add(new JLabel("    description:            "));
        txt2.setEditable(false);

        JPanel info3 = new JPanel();
        info3.setLayout(new BorderLayout(10, 0));
        JTextArea txt3 = new JTextArea(p.getDescription(), 14, 20);//todo description text
        txt3.setLineWrap(true);
        txt3.setWrapStyleWord(true);
        info3.add(txt3, BorderLayout.CENTER);
        info3.add(new JLabel(), BorderLayout.PAGE_END);
        txt3.setEditable(false);
        if(p.getAppointments()!=null){
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm  E");
            System.out.println(dateFormat.format(p.time.get(1).getTime()));//todo add time format xd
        }
        panel.setLayout(new BorderLayout(10, 10));
        JPanel info0 = new JPanel();
        info0.setLayout(new GridLayout(0, 1));
        info0.add(info);
        info0.add(info2);
        panel.add(info0, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(info3, BorderLayout.CENTER);
        panel.add(new JLabel(""), BorderLayout.BEFORE_LINE_BEGINS);
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
        addInfoF.add(tabPatients(1));
        addInfoF.setLocation(100, 150);
        addInfoF.setLocationByPlatform(true);
        addInfoF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addInfoF.setVisible(true);
        return "";
    }

    public void tick(){
        timeDisp.setText("Time: " + (mc.dateFormat.format(mc.cal.getTime())));
        if(checkTime != mc.cal.get(Calendar.DAY_OF_YEAR)){
            int indexOfTab = tabs.getSelectedIndex();
            panelSh.removeAll();
            tabs.add("Schedule", tabSchedule());
            tabs.remove(2);
            tabs.setSelectedIndex(indexOfTab);
            // aaa=10;
            checkTime = mc.cal.get(Calendar.DAY_OF_YEAR);
        }
        frame.repaint();

        // System.out.println(mc.cal.get(Calendar.DAY_OF_WEEK)); // TODO will need this ;)
    }
}
