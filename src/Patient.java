public class Patient {

    private String name;
    private String surName;
    private String id;
    private int insurance;


    public Patient(String name, String surName, String id, int insurance){
        this.name = name;
        this.surName = surName;
        this.id = id;
        this.insurance = insurance;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", id='" + id + '\'' +
                ", insurance=" + insurance +
                '}';
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }


}
