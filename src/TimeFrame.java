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
        JPanel panel = new JPanel();
        Font font = new Font("Times New Roman", 0, 24);

        window.setResizable(false);
        window.setDefaultCloseOperation(0);
        window.setSize(400, 110);
       // window.getContentPane().setLayout(new GridLayout(2, 1));
        window.setVisible(true);
        window.setAlwaysOnTop(true);
        //window.pack();

        panel.setBackground(Color.CYAN);


        time = new JLabel(mc.dateFormat.format(mc.cal.getTime()));
        JButton pDay = new JButton("plus day");
        JButton pWeek = new JButton("plus week");
        JButton pMonth = new JButton("plus month");
        JButton pYear = new JButton("plus year");


        panel.add(new JLabel("  Current Time:")).setFont(font);
        panel.add(time).setFont(font);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));

        pDay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pDay Pressed");
                mc.cal.add(Calendar.HOUR, 24);
            }

        });
        panel.add(pDay);

        pWeek.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pWeek Pressed");
                mc.cal.add(Calendar.HOUR, 24*7);
            }

        });
        panel.add(pWeek);
        pMonth.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pMonth Pressed");
                mc.cal.add(Calendar.MONTH, 1);
            }

        });
        panel.add(pMonth);

        pYear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pYear Pressed");
                mc.cal.add(Calendar.YEAR, 1);
            }

        });
        panel.add(pYear);

        window.add(panel);

    }

    public void tick(){
       time.setText((mc.dateFormat.format(mc.cal.getTime())));
    }
}
//