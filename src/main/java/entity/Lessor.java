package main.java.entity;

import main.java.enuns.Gender;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "lessor")
public class Lessor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Rent rent;

    private String name;
    private String rg;
    private String cpf;
    private String telOne;
    private String telTwo;

    @Temporal(TemporalType.DATE)
    @Column(name = "born_date")
    private Date bornDate;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelOne() {
        return telOne;
    }

    public void setTelOne(String telOne) {
        this.telOne = telOne;
    }

    public String getTelTwo() {
        return telTwo;
    }

    public void setTelTwo(String telTwo) {
        this.telTwo = telTwo;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    @Override
    public String toString() {
        String out = "Lessor{\n" +
                "id=" + id +
                ", \nname='" + name + '\'' +
                ", \nbornDate='" + bornDate + '\'' +
                ", \nrg='" + rg + '\'' +
                ", \ncpf='" + cpf + '\'' +
                ", \ntelOne='" + telOne + '\'' +
                ", \ntelTwo='" + telTwo + '\'' +
                ", \ngender=" + gender;
        if (rent != null) {
            out += ", \nrentID=" + rent.getId();
        } else {
            out += ", \nrentID= NULL";
        }
        out +=  '}';
        return out;
    }
}
