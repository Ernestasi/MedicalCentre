public class Human {
    private String name;
    private String surName;
    public Human(String name, String surName){
        this.name = name;
        this.surName = name;
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
}
