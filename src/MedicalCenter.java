import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

//
public class MedicalCenter  extends Canvas implements Runnable{

    ArrayList<Doctor> doctors =new ArrayList<>();
    ArrayList<Patient> patients = new ArrayList<>();
    ArrayList<Disease> diseases = new ArrayList<>();

    private Registration reg;
    private ReadData rd;
    private MainFrame mFrame;
    private Login log;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    Calendar cal = Calendar.getInstance();


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
        final double amountOfTicks = 2.0;
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

                cal.add(Calendar.MINUTE, 1);
                //System.out.println(updates + " Ticks, Fps " + frames);
                updates = 0;
                frames = 0;
            }

        }
        stop();
    }

    private void tick(){

    }

    private void render(){
        mFrame.render(this);
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




    public void init( MedicalCenter  medCent){

        rd = new ReadData();
        reg = new Registration();
        mFrame = new MainFrame();

        rd.readDoctors(doctors);
        rd.readPatients(patients);
        rd.readDisease(diseases);



        log = new Login();
        int a = log.render();
        if(a == 1 || a == 2) {
            mFrame.createWindow(this);
        }
        reg.render( "39702150000" );




        //for(Doctor d : doctors ){
         //   System.out.println(d);
        //}

       // for(Patient p: patients){
           // System.out.println(p);
       // }
        for(Disease d: diseases){
            System.out.println(d);
        }
        medCent.start();
    }


    public static void  main(String[] args) {

        MedicalCenter medCent = new  MedicalCenter();
        System.out.println("Application started!!");
        medCent.init(medCent);

    }
}