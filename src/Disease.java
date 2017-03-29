//alenskas
public class Disease {
    private String name;
    private String time;
    private double price;
    private int meetings;
    private int national;
    private int personal;

    public Disease(String name, String time, double price, int meetings, int national, int personal) {
        this.name = name;
        this.time = time;
        this.price = price;
        this.meetings = meetings;
        this.national = national;
        this.personal = personal;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", price=" + price +
                ", meetings=" + meetings +
                ", national=" + national +
                ", personal=" + personal +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMeetings() {
        return meetings;
    }

    public void setMeetings(int meetings) {
        this.meetings = meetings;
    }

    public int getNational() {
        return national;
    }

    public void setNational(int national) {
        this.national = national;
    }

    public int getPersonal() {
        return personal;
    }

    public void setPersonal(int personal) {
        this.personal = personal;
    }
}
