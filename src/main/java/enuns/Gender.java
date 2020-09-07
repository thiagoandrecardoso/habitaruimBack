package main.java.enuns;

public enum Gender {
    MALE("Masculino"),
    FEMALE("Feminino"),
    OTHERS("Outro");

    private String gender;

    private Gender(String gender) {
        this.gender = gender;
    }
    
    public String toString() {
        return this.gender;
    }
}
