import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

public class Main  extends Canvas implements Runnable{

    private Doctor[] doctors;

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int SCALE = 1;
    public final String TITLE = "Medical Centre Application";

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
        ReadData rd = new ReadData();
        rd.readDoctors(doctors);
        for(Doctor d : doctors ){
            System.out.println(doctors);
        }
        rd.readPatients();
        createWindow(main);
    }


    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("Hello World!");
        main.init(main);

    }

}