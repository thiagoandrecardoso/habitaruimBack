package com.habitarium.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "monthPaid")
public class MonthPaid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;
    private float value;
    private boolean isPaid;

    @ManyToOne
    private Rent rent;

    public MonthPaid() {}

    public MonthPaid(Date date, float value, boolean isPaid, Rent rent) {
        this.date = date;
        this.value = value;
        this.rent = rent;
        this.isPaid = isPaid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return rent.getLessor().getName() + "  Data: " + format.format(date) + "  Valor: R$" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonthPaid monthPaid = (MonthPaid) o;
        Calendar thisDate = Calendar.getInstance();
        Calendar monthPaidDate = Calendar.getInstance();
        thisDate.setTime(date);
        monthPaidDate.setTime(monthPaid.getDate());

        return thisDate.get(Calendar.MONTH) == monthPaidDate.get(Calendar.MONTH)
                && thisDate.get(Calendar.YEAR) == monthPaidDate.get(Calendar.YEAR);
    }

    public String dateString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }
}
