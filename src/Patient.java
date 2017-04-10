//
public class Patient extends Human {


    private String id;
    private int insurance;
    private String insType;
    public String description;
    //  String name
    //  String surName

    public Patient(String name, String surName, String id, int insurance, String description){
        setName(name);
        setSurName(surName);
        this.id = id;
        this.insurance = insurance;
        insType = intToIns(insurance);
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%1$-"+ 15 + "s", id)
                + "| " + String.format("%1$-"+ 15 + "s", getName()) + "| " + String.format("%1$-"+ 20 + "s", getSurName()) + " " +  String.format("%1$-"+ 20 + "s", insType);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
