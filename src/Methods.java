//maybe will need this for global methods

import java.io.*;
import java.util.ArrayList;

public class Methods{
    MedicalCenter mc;
    public void registerPatient(String docID, String patID, String chosenTime, MedicalCenter mc) {

        BufferedWriter bw = null;
        FileWriter fw = null;
        boolean exists = false;
        try {
            boolean checkIfGoodId = false;
            for (Patient p : mc.patients) {
                if ((p.getId().equals(patID) && (p.appointments == null))) {
                    fw = new FileWriter("./src/Data/Dates.txt", true);
                    bw = new BufferedWriter(fw);
                    String string = "\n" + patID + "," + docID + "_" + chosenTime;
                    bw.write(string);
                    exists = true;
                }
                if(p.getId().equals(patID))
                    checkIfGoodId = true;
            }
            if(!checkIfGoodId)
                System.out.println("NERA TOKIO PACIENTO!!!!");
        }
        catch (IOException e) {
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

        if(exists == false){
            try{
                File inputFile = new File("./src/Data/Dates.txt");
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                String lineToRemove = null;
                for(Patient p : mc.patients){
                    if(p.getId().equals(patID)){
                        String tempString = patID + p.appointments;
                        tempString = tempString.replace("[", " ");
                        tempString = tempString.replace(" ", ",");
                        tempString = tempString.replace("]","");
                        lineToRemove = tempString;
                    }
                }
                String currentLine;
                ArrayList<String> tempList = new ArrayList<String>();
                while((currentLine = reader.readLine()) != null){
                    if(currentLine.equals(lineToRemove)) {
                        tempList.add(currentLine+ ","+docID+"_"+chosenTime);
                    } else tempList.add(currentLine);
                }
                fw = new FileWriter("./src/Data/Dates.txt");
                bw = new BufferedWriter(fw);
                try{
                    for(int i = 0; i < tempList.size(); i++){
                        bw.write(tempList.get(i) + "\n");
                    }
                }catch (IOException e) {
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

            }catch(FileNotFoundException ex){
                ex.printStackTrace();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }

        }

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
