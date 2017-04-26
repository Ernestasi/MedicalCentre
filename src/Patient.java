import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//
public class Patient extends Human{


    private String id;
    private int insurance;
    private String insType;
    public String description;
    public ArrayList<String> appointments;
    public ArrayList<String> docId;
    public ArrayList<Calendar> time;

    public Patient(String name, String surName, String id, int insurance, String description){
        setName(name);
        setSurName(surName);
        this.id = id;
        this.insurance = insurance;
        insType = intToIns(insurance);
        this.description = description;

        docId = new ArrayList<>();
        time = new ArrayList<>();
    }

    @Override
    public String toString(){
        return String.format("%1$-" + 15 + "s", id)
                + "| " + String.format("%1$-" + 15 + "s", getName()) + "| " + String.format("%1$-" + 20 + "s", getSurName()) + " " + String.format("%1$-" + 20 + "s", insType);
    }

    private String intToIns(int i){
        if(i == 0){
            return "None";
        }else if(i == 1){
            return "National";
        }else if(i == 2){
            return "Personal";
        }else{
            return "Both";
        }
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public int getInsurance(){
        return insurance;
    }

    public void setInsurance(int insurance){
        this.insurance = insurance;
    }

    public String getInsType(){
        return insType;
    }

    public void setInsType(String insType){
        this.insType = insType;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public ArrayList<String> getAppointments(){
        return appointments;
    }

    public void setAppointments(String appointments){
        if(this.appointments == null)
            this.appointments = new ArrayList<>();
        this.appointments.add(appointments);
        AppointmentToTime();
    }

    public ArrayList<String> getDocId(){
        return docId;
    }

    public void setDocId(ArrayList<String> docId){
        this.docId = docId;
    }

    public ArrayList<Calendar> getTime(){
        return time;
    }

    public void setTime(ArrayList<Calendar> time){
        this.time = time;
    }

    public void AppointmentToTime(){
        time = null;
        docId = null;
        time= new ArrayList<>();
        docId= new ArrayList<>();
        for(String s : appointments){
            String[] temp = s.split("_");
            docId.add(temp[0]);
            //1_2017-05-01_10:30
            //1
            //2017-05-01
            String[] t = temp[1].split("-");
            String[] t2 = temp[2].split(":");
            Calendar tempCal =  Calendar.getInstance();
            tempCal.set(Calendar.YEAR, Integer.parseInt(t[0]));
            tempCal.set(Calendar.MONTH, Integer.parseInt(t[1])-1);
            tempCal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(t[2]));
            tempCal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(t2[0]));
            tempCal.set(Calendar.MINUTE, Integer.parseInt(t2[1]));
            time.add(tempCal);

        }
    }
}
