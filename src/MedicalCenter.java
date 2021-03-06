import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MedicalCenter extends Canvas implements Runnable{

    ArrayList<Doctor> doctors = new ArrayList<>();
    ArrayList<Patient> patients = new ArrayList<>();

    private DataMethods rd;
    Login log;
    TimeFrame tFrame;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm  E");
    Calendar cal = Calendar.getInstance();

    DoctorsFrame docFrame;
    PatientsFrame patFrame;

    private Thread thread;
    private boolean running = false;

    private synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run(){

        long lastTime = System.nanoTime();
        final double amountOfTicks = 2.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running){
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
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                cal.add(Calendar.MINUTE, 1);
                updates = 0;
                frames = 0;
            }

        }
        stop();
    }

    private void tick(){
        if(docFrame != null)
            docFrame.tick();
        tFrame.tick();
        if(patFrame != null)
            patFrame.tick();
    }

    private void render(){
    }

    private synchronized void stop(){
        if(!running)
            return;
        running = false;
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.exit(1);
    }


    public void init(MedicalCenter medCent){
        rd = new DataMethods();
        tFrame = new TimeFrame(this);


        medCent.start();
        tFrame.OpenTime();
        rd.readDoctors(doctors);
        rd.readPatients(patients);
        rd.readAppointment(patients);

        log = new Login(this);
        log.render();

    }


    public static void main(String[] args){

        MedicalCenter medCent = new MedicalCenter();
        System.out.println("Application started!!");
        medCent.init(medCent);

    }
}