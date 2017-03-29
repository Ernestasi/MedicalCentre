import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ReadData{

   // Doctor doc;

    public void readDoctors(List doc){
        try {
           BufferedReader br = new BufferedReader(new FileReader("./src/Data/Doctors.txt"));
                String line;
                int i = 0;
                while((line = br.readLine()) != null) {
                    String[] word = line.split(" ");
                  doc.add(new Doctor(word[0], word[1], Integer.parseInt(word[2]), word[3]));
                }
                br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readPatients(List pat){
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/Data/Patients.txt"));
                String line;
                int i = 0;
                while( (line = br.readLine()) != null){
                    String[] word = line.split(",");
                    pat.add(new Patient(word[0], word[1], word[2], Integer.parseInt(word[3])));
                }
        }  catch(IOException e) {
            e.printStackTrace();
        }
    }
}
