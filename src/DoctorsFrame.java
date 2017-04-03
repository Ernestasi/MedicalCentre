import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
//
public class DoctorsFrame{

    MedicalCenter mc;

    public DoctorsFrame(MedicalCenter mc){
        this.mc = mc;
    }

    public void render(){
        JFrame frame = new JFrame("Medical Centre Application for Doctors  ");
        JPanel panelD = new JPanel();
        JPanel panelP = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        JTabbedPane tabs = new JTabbedPane();
        panelD.setLayout(new GridLayout(mc.doctors.size(), 1));
        panelP.setLayout(new GridLayout(mc.patients.size(), 4));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 600);
        frame.setResizable(false);
        frame.requestFocus();



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

        scrollPane =  new JScrollPane(panelD);
        JLabel lab = new JLabel();
        lab.setLayout(new BorderLayout());
        lab.add(new JLabel(String.format("%1$"+ 19 + "s","Name")
                +String.format("%1$"+ 29 + "s", "Surname")
                +String.format("%1$"+ 30 + "s", "Cab")
                +String.format("%1$"+ 36 + "s", "Specelizatoin")
        ), BorderLayout.NORTH);
        lab.add(scrollPane);
        tabs.addTab("Doctors List", lab);

        for(Patient p : mc.patients){
            JButton but = new JButton();
            but.setHorizontalAlignment(SwingConstants.LEFT );
            but.add(new JLabel(p.getId()));
            but.add(new JLabel(String.format("%1$"+ 50 + "s", p.getName())));
            but.add(new JLabel(String.format("%1$"+ 90 + "s", p.getSurName())));
            but.add(new JLabel(String.format("%1$"+ 130 + "s", p.getInsType())));
            but.setPreferredSize(new Dimension( 300, 30));
            panelP.add(but);
        }

        JScrollPane scrollPaneP =  new JScrollPane(panelP);
        JLabel labP = new JLabel();
        labP.setLayout(new BorderLayout());
        labP.add(new JLabel(
                String.format("%1$"+ 17 + "s","ID")
                        +String.format("%1$"+ 36 + "s", "Name")
                        +String.format("%1$"+ 33 + "s", "Surname")
                        +String.format("%1$"+ 32 + "s", "Insurnace")
                        ), BorderLayout.NORTH);
        labP.add(scrollPaneP);
        tabs.addTab("Patients", labP);
        tabs.setSelectedIndex(0);
        frame.add(tabs, BorderLayout.CENTER);




        frame.setVisible(true);
    }
}
