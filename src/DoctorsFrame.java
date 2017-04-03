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
            JButton button = new JButton( d.toString() );
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setPreferredSize(new Dimension( 300, 30));
            panelD.add(button);
        }
        for(Patient p : mc.patients){
            JButton but = new JButton();
            but.setHorizontalAlignment(SwingConstants.LEFT );
            but.add(new JLabel(p.getId()));
            but.add(new JLabel(String.format("%1$"+ 50 + "s", p.getName())));
            but.add(new JLabel(String.format("%1$"+ 90 + "s", p.getSurName())));
            but.add(new JLabel(String.format("%1$"+ 130 + "s", p.getInsType())));
            //l.setText(String.format("%1$-"+ 15 + "s", p.getId()) + String.format("%-15s", p.getName()).replace(' ', '_') + String.format("%1$-"+ 15 + "s", p.getSurName()) );
            but.setPreferredSize(new Dimension( 300, 30));
            panelP.add(but);
        }

        //frame.getContentPane().add(new JScrollPane(panelD));
        tabs.addTab("Doctors", new JScrollPane(panelD));
        tabs.addTab("Patients", new JScrollPane(panelP));
        tabs.setSelectedIndex(0);
        frame.add(tabs, BorderLayout.CENTER);




        frame.setVisible(true);
    }
}
