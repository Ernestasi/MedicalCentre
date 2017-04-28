//maybe will need this for global methods

import java.io.*;
import java.util.ArrayList;

public class Methods{
    MedicalCenter mc;
    public void registerPatient(String docID, String patID, String chosenTime, MedicalCenter mc) {

        boolean checkIfGoodId = false;
        boolean exists = false;
        for (Patient p : mc.patients) {
            if ((p.getId().equals(patID) && (p.appointments == null))) {
                p.appointments.add(patID + "," + docID + "_" + chosenTime);
                exists = true;
            }
            if (p.getId().equals(patID))
                checkIfGoodId = true;
        }
        if(!checkIfGoodId)
            System.out.println("Toks pacientas neegzistuoja sistemoje");
        if(!exists){
            String lineToReplace = null;
            for(Patient p : mc.patients){
                if(p.getId().equals(patID)){
                    p.appointments.add(docID + "_" + chosenTime);
                }
            }
        }
        updateDatesFile(mc.patients);
        System.out.println("Added sucessfuly");
    }


    public void updateDatesFile(ArrayList<Patient> patients){
// 20926475361,0_2017-04-24_11:00,0_2017-04-24_10:00
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

            String string = "";
            for(Patient p : patients){
                //Ona_Petraite_99286715699_0_ _
                String a = "_";
                string = string + p.getName() + a + p.getSurName() + a + p.getId() + a + p.getInsurance() + a + p.getDescription() + a + "\n";
            }
        BufferedWriter writer = null;
        try
        {
            writer = new BufferedWriter( new FileWriter( "./src/Data/Patients.txt"));
            writer.write( string);

        }
        catch ( IOException e)
        {
        }
        finally
        {
            try
            {
                if ( writer != null)
                    writer.close( );
            }
            catch ( IOException e)
            {
            }
        }
    }
}
