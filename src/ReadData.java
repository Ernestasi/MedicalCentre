import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadData{

   // Doctor doc;

    public void readDoctors(Doctor[] doc){
        try {
           BufferedReader br = new BufferedReader(new FileReader("./src/Data/Doctors.txt"));
                String line;
                int i = 0;
                while ((line = br.readLine()) != null) {
                  String[] word = line.split(" ");
                  doc[i] = new Doctor(word[0], word[1], Integer.parseInt(word[2]), word[3]);
                  i++;
                  //System.out.println(word[0] +  word[1] + Integer.parseInt(word[2]) + word[3]);
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readPatients(){

    }
}
