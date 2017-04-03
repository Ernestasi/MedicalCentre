//
public class Patient {

    private String name;
    private String surName;
    private String id;
    private int insurance;
    private String insType;


    public Patient(String name, String surName, String id, int insurance){
        this.name = name;
        this.surName = surName;
        this.id = id;
        this.insurance = insurance;
        insType = intToIns(insurance);
    }

    @Override
    public String toString() {
        return String.format("%1$-"+ 15 + "s", id)
                + "| " + String.format("%1$-"+ 15 + "s", name) + "| " + String.format("%1$-"+ 20 + "s", surName) + " " +  String.format("%1$-"+ 20 + "s", insType);
    }

    private String intToIns(int i){
        if(i==0){
            return "Type0";
        }else if(i==1){
            return "Type1";
        }else if(i==2){
            return "Type2";
        }else{
            return "Type3";
        }
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

    public String getInsType() {
        return insType;
    }

    public void setInsType(String insType) {
        this.insType = insType;
    }
}
