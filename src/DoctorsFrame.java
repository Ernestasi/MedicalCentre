import javafx.embed.swing.JFXPanel;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.EventListener;
import javax.swing.*;
//
public class DoctorsFrame{
    int currentDoctor = 0; //TODO reikia kad reaguotu kuris daktaras!!!!

    MedicalCenter mc;
    Doctor d;
    JFrame frame;

    JLabel timeDisp = new JLabel();
    String string1 = "a";
    String string2 = "bb";
    JPanel panelSh = new JPanel();

    JTabbedPane tabs = new JTabbedPane();

    public DoctorsFrame(MedicalCenter mc){
        this.mc = mc;
    }

    public void init(){
        d = mc.doctors.get(currentDoctor);
        frame = new JFrame("Medical Centre Application for Doctors  ");



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(900, 200);
        //frame.setLocationRelativeTo(null);
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
            but.setHorizontalAlignment(SwingConstants.LEFT );
            but.add(new JLabel(String.format("%1$"+ 15 + "s", d.getName())));
            but.add(new JLabel( String.format("%1$"+ 50 + "s", d.getSurName())));
            but.add(new JLabel( String.format("%1$"+ 90 + "s", d.getCab())));
            but.add(new JLabel( String.format("%1$"+ 130 + "s", "Specelizatoin")));
            but.setPreferredSize(new Dimension( 300, 30));
            but.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Pressed on doctor: " + d.getName() + " " + d.getSurName());
                }
            });
            panelD.add(but);
        }

        JScrollPane scrollPane =  new JScrollPane(panelD);
        JLabel lab = new JLabel();
        lab.setLayout(new BorderLayout());
        lab.add(new JLabel(String.format("%1$"+ 19 + "s","Name")
                +String.format("%1$"+ 29 + "s", "Surname")
                +String.format("%1$"+ 30 + "s", "Cab")
                +String.format("%1$"+ 36 + "s", "Specelizatoin")
        ), BorderLayout.NORTH);
        lab.add(scrollPane);

        return lab;
    }
    private JLabel tabPatients(){

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        for(Patient p : mc.patients){
            JButton but = new JButton();
            but.setHorizontalAlignment(SwingConstants.LEFT );
            but.add(new JLabel(p.getId()));
            but.add(new JLabel(String.format("%1$"+ 50 + "s", p.getName())));
            but.add(new JLabel(String.format("%1$"+ 90 + "s", p.getSurName())));
            but.add(new JLabel(String.format("%1$"+ 130 + "s", p.getInsType())));
            but.setPreferredSize(new Dimension( 300, 30));
            but.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Pressed on patient: " + p.getName() + " " + p.getSurName());
                    patientInfoFrame(p);
                }
            });
            panel.add(but);
        }

        JScrollPane scrollPaneP =  new JScrollPane(panel);
        JLabel lab = new JLabel();
        lab.setLayout(new BorderLayout());
        lab.add(new JLabel(
                String.format("%1$"+ 17 + "s","ID")
                        +String.format("%1$"+ 36 + "s", "Name")
                        +String.format("%1$"+ 33 + "s", "Surname")
                        +String.format("%1$"+ 32 + "s", "Insurnace")
        ), BorderLayout.NORTH);
        lab.add(scrollPaneP);
        return lab;
    }

    private JLabel tabSchedule(){

        panelSh.setLayout(new GridLayout(0, 2, 50, 60));
        panelSh.setPreferredSize(new Dimension(0,1000));
        panelSh.add(timeGraph(1));
        panelSh.add(timeGraph(2));
        panelSh.add(timeGraph(3));
        panelSh.add(timeGraph(4));
        panelSh.add(timeGraph(5));
        panelSh.add(timeGraph(6));

        JScrollPane scrollPaneP = new JScrollPane(panelSh);

        JLabel lab = new JLabel();
        lab.setLayout(new BorderLayout());
        lab.add(timeDisp,BorderLayout.NORTH);
        lab.add(scrollPaneP);


        return lab;
    }

    private JLabel timeGraph(int day){
        String name;
        if(day==1)
            name = "Monday: ";
        else if(day==2)
            name = "Tuesday: ";
        else if(day==3)
            name = "Wednesday: ";
        else if(day==4)
            name = "Thursday: ";
        else if(day==5)
            name = "Friday: ";
        else if(day==6)
            name = "Saturday: ";
        else
            name = "Sunday: ";
        day-=1;
        JLabel temp = new JLabel();

        temp.setLayout(new BorderLayout());
        if(!(d.getTimeDay(day).equals("-"))){

            temp.add(new JLabel(String.format("%1$-" + 37 + "s", name) + String.format("%1$" + 11 + "s",d.getTimeDay(day)).replace(" ", "0")), BorderLayout.NORTH);
            //temp.add(new JLabel());
            {
                String[] s = (d.getTimeDay(day).split("-|:"));
                double time0 = Integer.parseInt(s[0]) + Double.parseDouble(s[1]) / 60;
                double time1 = Integer.parseInt(s[2]) + Double.parseDouble(s[3]) / 60;
                JPanel pan = new JPanel();
                pan.setLayout(new GridLayout(0, 1));
                JPanel pan2 = new JPanel();
                pan2.setLayout(new GridLayout(0, 1));
                for (double i= (time0%1==0)?0:0.5;  i < time1 - time0; i += 0.5) {
                    pan.add(new JLabel(  String.format("%1$" + 2 + "s", (int)(Integer.parseInt(s[0]) + i)).replace(" ", "0") + ":" + ((i%1==0)?"00":"30")
                            + " - " + String.format("%1$" + 2 + "s", (int)(Integer.parseInt(s[0]) + i +0.5)).replace(" ", "0") + ":" + ((i%1==0)?"30":"00")));
                    pan2.add(patientBut(day, (int)i*2), BorderLayout.CENTER);
                }
                //pan2.add(patient(day), BorderLayout.CENTER);
                temp.add(pan, BorderLayout.WEST);
                temp.add(pan2, BorderLayout.EAST);
            }

            return temp;
        }else{
            temp.add(new JLabel(String.format("%1$-" + 12 + "s", name) +d.getTimeDay(day) ), BorderLayout.NORTH);
            temp.add(new JLabel("Chilling at home"));

            return temp;
        }
    }


    private JPanel patientBut(int day, int i){
        Patient p = mc.patients.get(i);
        JPanel jb = new JPanel();
        jb.setLayout(new BorderLayout());
        JButton but;
        if(mc.cal.get(Calendar.DAY_OF_WEEK)-1 == day+1){
            but = new JButton("⌂");
            jb.add(new JLabel(p.getName() + " " + p.getSurName()), BorderLayout.WEST);
            jb.add(but, BorderLayout.EAST);
            but.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Cheking patient: " + p.getName() + " " + p.getSurName());
                }
            });
        }else{
            but = new JButton("add");
            jb.add(new JLabel(String.format("%1$-"+ 20 + "s","----")), BorderLayout.WEST);
            jb.add(but, BorderLayout.EAST);
            but.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Setting up new appointment");
                }
            });
        }
        return jb;
    }
int checkTime = 0;//useless comment
    public void tick(){
        timeDisp.setText("Time: " + (mc.dateFormat.format(mc.cal.getTime())));
        //System.out.println(aaa);
      //  aaa--;
        if(checkTime != mc.cal.get(Calendar.WEEK_OF_YEAR)){
            int indexOfTab = tabs.getSelectedIndex();
            panelSh.removeAll();
            tabs.add("Schedule", tabSchedule());
            tabs.remove(2);
            tabs.setSelectedIndex(indexOfTab);
           // aaa=10;
            checkTime = mc.cal.get(Calendar.WEEK_OF_YEAR);
        }
        frame.repaint();

        // System.out.println(mc.cal.get(Calendar.DAY_OF_WEEK)); // TODO will need this ;)
    }

    private void patientInfoFrame(Patient p){
        //JDialog infoF = new JDialog(frame, p.getName() + " " + p.getSurName(), Dialog.ModalityType.DOCUMENT_MODAL);
        JFrame infoF = new JFrame(p.getName() + " "+  p.getSurName());
        infoF.setSize(400, 500);
        infoF.setLocationRelativeTo(null);
        infoF.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();

        JPanel info = new JPanel();
        info.add(new JLabel("Patient: "));
        JTextField txt = new JTextField( p.getName() + " " + p.getSurName(),15);
        txt.setEditable(false);
        info.add(txt);
        info.add(new JLabel("  ID: "));
        JTextField txt1 = new JTextField(p.getId(),8);
        txt1.setEditable(false);
        info.add(txt1);

        JPanel info2 = new JPanel();
        info2.add(new JLabel("Insurance: "));
        JTextField txt2 = new JTextField(p.getInsType(), 10);
        info2.add(txt2);
        txt2.setEditable(false);

        JPanel info3 = new JPanel();
        info3.add(new JLabel("Description: "));
        JTextField txt3 = new JTextField(p.getInsType(), 10);
        info3.add(txt3);
        txt3.setEditable(false);

        panel.setLayout(new BorderLayout(10, 10));
        panel.add(info, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(info2);



        infoF.add(panel);
        infoF.setVisible(true);
    }
}
