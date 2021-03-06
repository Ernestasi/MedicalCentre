import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;


public class DataMethods{


    public void readDoctors(List doc){
        try{
            BufferedReader br = new BufferedReader(new FileReader("./src/Data/Doctors.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] word = line.split(" ");
                doc.add(new Doctor(word[0], word[1], Integer.parseInt(word[2]), word[3].replace("_", " "), word[4], doc.size()));
            }
            br.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void readPatients(List pat){
        try{
            BufferedReader br = new BufferedReader(new FileReader("./src/Data/Patients.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] word = line.split("_");
                pat.add(new Patient(word[0], word[1], word[2], Integer.parseInt(word[3]), word[4]));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void readAppointment(List<Patient> pat){
        try{
            BufferedReader br = new BufferedReader(new FileReader("./src/Data/Dates.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] pID = line.split(",");
                String tempID = pID[0];
                for(Patient p : pat){
                    if(p.getId().equals(tempID)){
                        p.appointments = new ArrayList<>();
                        for(int i = 1; i < pID.length; i++){
                            p.appointments.add(pID[i]);
                        }
                        p.AppointmentToTime();
                        break;
                    }
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void updateDatesFile(ArrayList<Patient> patients){
        int counter = 0;
        for(Patient p : patients){
            if(p.time.size() != 0){
                Calendar lastDate = p.time.get(p.time.size()-1);

                for(Calendar c : p.time){
                    if(c.getTime().equals(lastDate.getTime())){
                        counter++;
                    }
                }

                if(counter > 1){
                    for(int i = 0; i < p.appointments.size(); i++){
                        if(p.time.get(i).toString().equals(lastDate.toString())){
                            p.appointments.remove(i);
                            p.AppointmentToTime();
                            break;
                        }
                    }
                    //JFrame start
                    JFrame warningWindow = new JFrame();
                    warningWindow.setLocation(250,250);
                    warningWindow.setSize(400,100);
                    warningWindow.setAlwaysOnTop(true);
                    warningWindow.setLayout(new GridLayout(1,1));
                    warningWindow.add(new JLabel("Old visit at this time was CANCELED"));
                    warningWindow.setVisible(true);
                    //JFrame end
                }

                counter = 0;
            }
        }

        System.out.println("Dates file has been updated");
        BufferedWriter bw = null;
        FileWriter fw = null;
        try{
            fw = new FileWriter("./src/Data/Dates.txt");
            bw = new BufferedWriter(fw);
            for(Patient p : patients){
                if(p.getAppointments() != null){
                    String string = p.getId();
                    for(String times : p.appointments){
                        string = string + "," + times;
                    }
                    string = string + "\n";
                    bw.write(string);
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(bw != null)
                    bw.close();
                if(fw != null)
                    fw.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }

    }

    public void updatePatientsFile(ArrayList<Patient> patients){
        System.out.println("Patients file has been updated");
        String string = "";
        for(Patient p : patients){
            //Ona_Petraite_99286715699_0_ _
            String a = "_";
            string = string + p.getName() + a + p.getSurName() + a + p.getId() + a + p.getInsurance() + a + p.getDescription() + a + "\n";
        }
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter("./src/Data/Patients.txt"));
            writer.write(string);

        }catch(IOException e){
        }finally{
            try{
                if(writer != null)
                    writer.close();
            }catch(IOException e){
            }
        }
    }
}