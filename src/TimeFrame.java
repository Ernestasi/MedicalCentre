import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

public class TimeFrame {


    MedicalCenter mc;
    JLabel time;

    public TimeFrame(MedicalCenter mc){
        this.mc = mc;
    }

    public void OpenTime() {
        JFrame window = new JFrame("Time Simulation");

        window.setResizable(false);
        window.setDefaultCloseOperation(0);
        window.setSize(380, 180);
        window.getContentPane().setLayout(new GridLayout(3, 3));
        window.setVisible(true);
        window.setAlwaysOnTop(true);
        //window.pack();
        time = new JLabel(mc.dateFormat.format(mc.cal.getTime()));
        JButton pDay = new JButton("plus day");
        JButton pWeek = new JButton("plus week");
        JButton pMonth = new JButton("plus month");
        JButton pYear = new JButton("plus year");
        window.add(new JLabel("  Current Time:"));
        window.add(time);
        window.add(pDay);
        window.add(pWeek);
        window.add(pMonth);
        window.add(pYear);

        pDay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pDay Pressed");
                mc.cal.add(Calendar.HOUR, 24);
            }

        });
        window.add(pDay);

        pWeek.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pWeek Pressed");
                mc.cal.add(Calendar.HOUR, 24*7);
            }

        });
        window.add(pWeek);

        pMonth.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pMonth Pressed");
                mc.cal.add(Calendar.MONTH, 1);
            }

        });
        window.add(pMonth);

        pYear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pYear Pressed");
                mc.cal.add(Calendar.YEAR, 1);
            }

        });
        window.add(pYear);

    }

    public void tick(){
       time.setText((mc.dateFormat.format(mc.cal.getTime())));
    }
}