public class Patient {

    private String name;
    private String surName;
    private int id;
    private int insurance;


    public Patient(String name, String surName, int id, int insurance){
        this.name = name;
        this.surName = surName;
        this.id = id;
        this.insurance = insurance;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }


}
