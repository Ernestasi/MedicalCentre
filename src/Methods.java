//maybe will need this for global methods

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
// 20926475361,0_2017-04-24_11:00,0_2017-04-24_10:00
public class Methods{

    public void updateDatesFile(ArrayList<Patient> patients){
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter("./src/Data/Dates.txt");
            bw = new BufferedWriter(fw);
            for(Patient p: patients){
                if(p.getAppointments()!=null){
                    String string = p.getId();
                    for(String times : p.appointments){
                        string = string +","+times;
                    }
                    string = string + "\n";
                    bw.write(string);
                }
            }
            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }

}
