import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.DayOfWeek;
import java.util.Calendar;
import javax.swing.*;
//
public class DoctorsFrame{
    int currentDoctor = 0; //TODO reikia kad reaguotu kuris daktaras!!!!

    MedicalCenter mc;
    JFrame frame;
    JLabel timeDisp = new JLabel();

    public DoctorsFrame(MedicalCenter mc){
        this.mc = mc;
    }

    public void init(){
        frame = new JFrame("Medical Centre Application for Doctors  ");

        JTabbedPane tabs = new JTabbedPane();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(900, 200);
        //frame.setLocationRelativeTo(null);
        frame.setSize(500, 600);
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
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 50, 60));

        Doctor d = mc.doctors.get(currentDoctor);

        panel.setPreferredSize(new Dimension(0,1000));
        panel.add(timeGraph(d, 0, "Monday: "));
        panel.add(timeGraph(d, 1,"Tuesday: "));
        panel.add(timeGraph(d, 2,"Wednesday: "));
        panel.add(timeGraph(d, 3,"Thursday: "));
        panel.add(timeGraph(d, 4,"Friday: "));
        panel.add(timeGraph(d, 5,"Saturday: "));

        JScrollPane scrollPaneP = new JScrollPane(panel);

        JLabel lab = new JLabel();
        lab.setLayout(new BorderLayout());
        lab.add(timeDisp,BorderLayout.NORTH);
        lab.add(scrollPaneP);


        return lab;
    }

    private JLabel timeGraph(Doctor d, int day, String name){

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
                    pan2.add(new JButton("penis xd"), BorderLayout.CENTER);
                }
                temp.add(pan, BorderLayout.WEST);
                temp.add(pan2, BorderLayout.EAST);
            }

            return temp;
        }else{
            temp.add(new JLabel(String.format("%1$-" + 12 + "s", name) +d.getTimeDay(day) ), BorderLayout.NORTH);
            temp.add(new JLabel("Laisvas nx"));
            return temp;
        }
    }

    public void tick(){
        timeDisp.setText("Time: " + (mc.dateFormat.format(mc.cal.getTime())));
       // System.out.println(mc.cal.get(Calendar.DAY_OF_WEEK)); // TODO will need this ;)
    }
}
