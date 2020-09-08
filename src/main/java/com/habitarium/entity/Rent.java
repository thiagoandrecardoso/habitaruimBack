package main.java.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "rent")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "rent")
    private Property property;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "rent")
    private Lessor lessor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rent")
    private List<MonthPaid> monthPaidList;

    @Temporal(TemporalType.DATE)
    @Column(name = "entrance_date")
    private Date entranceDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "exit_date")
    private Date exitDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "readjustment_date")
    private Date readjustmentDate;

    private float value;
    private int payDay;

    public Rent() {
    }

    public Rent(Long id, Property property, Lessor lessor, Date entranceDate, Date exitDate, Date readjustmentDate,
                float value, int payDay) {
        this.id = id;
        this.property = property;
        this.lessor = lessor;
        this.entranceDate = entranceDate;
        this.exitDate = exitDate;
        this.readjustmentDate = readjustmentDate;
        this.value = value;
        this.payDay = payDay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(Date entranceDate) {
        this.entranceDate = entranceDate;
    }

    public Date getExitDate() {
        return exitDate;
    }

    public void setExitDate(Date exitDate) {
        this.exitDate = exitDate;
    }

    public Date getReadjustmentDate() {
        return readjustmentDate;
    }

    public void setReadjustmentDate(Date readjustmentDate) {
        this.readjustmentDate = readjustmentDate;
    }

    public int getPayDay() {
        return payDay;
    }

    public void setPayDay(int payDay) {
        this.payDay = payDay;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Lessor getLessor() {
        return lessor;
    }

    public void setLessor(Lessor lessor) {
        this.lessor = lessor;
    }

    public List<MonthPaid> getMonthPaidList() {
        return monthPaidList;
    }

    public void setMonthPaidList(List<MonthPaid> monthPaidList) {
        this.monthPaidList = monthPaidList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (lessor != null && property != null) {
            sb.append("Nome " + lessor.getName() + "  ");
            sb.append("Valor R$: " + this.value + "  ");
            sb.append("Vencimento: " + this.payDay + "  ");
            sb.append("\n");
            sb.append("Rua/Av: " + property.getStreet() + "  ");
            sb.append("Nº: " + property.getPropertyNumber() + "  ");
            sb.append("Bairro: " + property.getNeighbour() + "  ");
            if(!property.getCondo().equals("")) sb.append("Cond: " + property.getCondo() + "  ");
            if(!property.getBlockCondo().equals("")) sb.append("Blo: " + property.getBlockCondo() + "  ");
            if(!property.getApartment().equals("")) sb.append("Apto: " + property.getApartment() + "");
        } else {
            sb.append("Aluguel incompleto: " +  this.id);
        }

        return sb.toString();
    }
}
