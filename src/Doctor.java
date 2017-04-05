import javax.print.Doc;
import java.util.Arrays;

public class Doctor extends Human{
    private int cab;
    private String time;
    private String[] timeDay;
    private String data;


    public Doctor(String name, String surName, int cab, String time){
        setName(name);
        setSurName(surName);
        this.cab = cab;
        this.time = time;
        toDay(time);
        constructData();
    }

    public Doctor(){

    }

    private void toDay(String time){
        timeDay = time.split("/");
    }

    @Override
    public String toString() {
        return  getName() +
                " " + getSurName() +
                "  " + cab ;
    }

    private void constructData(){
        data = getName() + " \t" + getSurName() + " \t" + cab + " \t" + timeDay;
    }

    public int getCab() {
        return cab;
    }

    public void setCab(int cab) {
        this.cab = cab;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        toDay(time);
    }

    public String getTimeDay(int i) {
        return timeDay[i];
    }

    public void setTimeDay(String[] timeDay) {
        this.timeDay = timeDay;
    }
}
