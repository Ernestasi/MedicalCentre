import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadData{

    // Doctor doc;

    public void readDoctors(List doc){
        try{
            BufferedReader br = new BufferedReader(new FileReader("./src/Data/Doctors.txt"));
            String line;
            int i = 0;
            while((line = br.readLine()) != null){
                String[] word = line.split(" ");
                doc.add(new Doctor(word[0], word[1], Integer.parseInt(word[2]), word[3]));
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

    public void readAppointment(ArrayList<Patient> pat){
        try{
            BufferedReader br = new BufferedReader(new FileReader("./src/Data/Dates.txt"));
            String line;
            while((line = br.readLine())     != null){
                String[] pID = line.split(",");
                for(int i=0; i<pat.size(); i++){
                    if(pat.get(i).getId() == pID[0]){
                        for(int j=1; j<pID.length; j++){
                            pat.get(i).getAppointments().add(pID[j]);
                        }
                    }
                }
            }


        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
//