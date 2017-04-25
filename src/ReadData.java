import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadData{

    public void readDoctors(List doc){
        try{
            BufferedReader br = new BufferedReader(new FileReader("./src/Data/Doctors.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] word = line.split(" ");
                doc.add(new Doctor(word[0], word[1], Integer.parseInt(word[2]), word[3], word[4]));
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

    public void readDisease(List dis){
        try{
            BufferedReader br = new BufferedReader(new FileReader("./src/Data/Disease.txt"));
            String line;
            while((line = br.readLine()) != null){
                String[] word = line.split(" ");
                dis.add(new Disease(word[0], word[1], Double.parseDouble(word[2]), Integer.parseInt(word[3]), Integer.parseInt(word[4]), Integer.parseInt(word[5])));
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
}