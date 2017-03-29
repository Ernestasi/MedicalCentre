import javax.swing.*;
import java.awt.*;

public class MainFrame {


    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final int SCALE = 1;
    private final String TITLE = "Medical Centre Application";

    public void createWindow( MedicalCenter  medCent){
        // MedicalCenter  medCent = new  MedicalCenter();

        medCent.setPreferredSize(new Dimension(WIDTH *SCALE, HEIGHT * SCALE));
        medCent.setMaximumSize(new Dimension(WIDTH *SCALE, HEIGHT * SCALE));
        medCent.setMinimumSize(new Dimension(WIDTH *SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame();
        frame.add( medCent);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();

    }


    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getSCALE() {
        return SCALE;
    }
}
