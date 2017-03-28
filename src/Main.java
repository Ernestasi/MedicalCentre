import javax.swing.*;
import java.awt.*;

public class Main  extends Canvas{

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int SCALE = 1;
    public final String TITLE = "Medical Centre Application";


    public void init(){
        requestFocus();


    }

    public static void main(String[] args) {
        Main main = new Main();

        main.setPreferredSize(new Dimension(WIDTH *SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame();
        frame.add(main);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        System.out.println("Hello World!");
        System.out.println("kaip eina?");
    }


}