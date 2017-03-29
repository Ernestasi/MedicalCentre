import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TimeFrame {

    MedicalCenter mc;
    JLabel time;

    public TimeFrame(MedicalCenter mc){
        this.mc = mc;
    }

    public void OpenTime(){
        JFrame window = new JFrame("Time Controll");
        window.setDefaultCloseOperation(0);
        //window.set
        window.setSize(320, 180);
        window.setResizable(false);
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

        pDay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pDay Pressed");
                mc.setTimeplus(1);
            }

        });
        window.add(pDay);

        pWeek.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pWeek Pressed");
                mc.setTimeplus(2);
            }

        });
        window.add(pWeek);

        pMonth.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pMonth Pressed");
                mc.setTimeplus(3);
            }

        });
        window.add(pMonth);

        pYear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pYear Pressed");
                mc.setTimeplus(4);
            }

        });
        window.add(pYear);

    }

    public void tick(){
        time.setText((mc.dateFormat.format(mc.cal.getTime())));
    }
}
