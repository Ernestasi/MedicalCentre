import javax.print.Doc;
import java.util.Arrays;

public class Doctor{
    private String name;
    private String surName;
    private int cab;
    private String time;
    private String[] timeDay;


    public Doctor(String name, String surName, int cab, String time){
        this.name = name;
        this.surName = surName;
        this.cab = cab;
        this.time = time;
        toDay(time);
    }

    public Doctor(){

    }

    private void toDay(String time){
        timeDay = time.split("/");
    }

    @Override
    public String toString() {
        return "Doctor name = " + name +
                ", surName = " + surName +
                ", cab = " + cab;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
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
}
