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
                while((line = br.readLine()) != null && i<10) {
                    line = br.readLine();
                    String[] word = line.split(" ");
                    i++;
                  doc.add(new Doctor(word[0], word[1], Integer.parseInt(word[2]), word[3]));
                }
                br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readPatients(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/Data/Patients.txt"));
                String line;
                int i = 0;
                while(i < 10);
        }  catch(IOException e) {
            e.printStackTrace();
        }
    }
}
