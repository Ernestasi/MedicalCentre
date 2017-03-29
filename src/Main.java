import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main  extends Canvas implements Runnable{

    ArrayList<Doctor> doctors =new ArrayList<>();

    private Registration reg;

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final int SCALE = 1;
    private final String TITLE = "Medical Centre Application";

    private Thread thread;
    private boolean running = false;

    private synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running){
            //System.out.println(" Ticks, Fps ");
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
                updates++;
                delta--;

            }
            render();
            frames++;
            //System.out.println(System.currentTimeMillis()- timer + " " );
            if(System.currentTimeMillis()- timer > 1000){
                timer += 1000;
                System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }

        }
        stop();
    }

    private void tick(){

    }

    private void render(){

    }

    private  synchronized  void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void createWindow(Main main){
        //Main main = new Main();

        main.setPreferredSize(new Dimension(WIDTH *SCALE, HEIGHT * SCALE));
        main.setMaximumSize(new Dimension(WIDTH *SCALE, HEIGHT * SCALE));
        main.setMinimumSize(new Dimension(WIDTH *SCALE, HEIGHT * SCALE));

        JFrame frame = new JFrame();
        frame.add(main);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        requestFocus();

        main.start();
    }

    public void init(Main main){
        reg = new Registration();
        reg.render( "15555" );
        ReadData rd = new ReadData();
        rd.readDoctors(doctors);
        for(Doctor d : doctors ){
            System.out.println(d);
        //System.out.println(doctors.get(0));
        }
        rd.readPatients();
        createWindow(main);
    }


    public static void main(String[] args) {
        Main medCent = new Main();
        System.out.println("Hello World!");
        medCent.init(medCent);

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