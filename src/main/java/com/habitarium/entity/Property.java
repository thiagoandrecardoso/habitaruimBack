package com.habitarium.entity;

import javax.persistence.*;

@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
//    @Column(nullable = true)
    private Rent rent;

    private String neighbour; //bairro
    private String street;
    private String propertyNumber;
    private String condo;
    private String blockCondo; //bloco do condominio
    private String city;
    private String apartment;

    public Rent getRent() {
        return rent;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNeighbour() {
        return neighbour;
    }

    public void setNeighbour(String neighbour) {
        this.neighbour = neighbour;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public String getCondo() {
        return condo;
    }

    public void setCondo(String condo) {
        this.condo = condo;
    }

    public String getBlockCondo() {
        return blockCondo;
    }

    public void setBlockCondo(String blockCondo) {
        this.blockCondo = blockCondo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        String apartmentStr = street + ", " + neighbour + ", " + condo + ", " + blockCondo + ", " + apartment;
        String houseStr = street + ", " + neighbour + ", " + "Nº " + propertyNumber + ", " + city;
        String condominiumStr = street + ", " + neighbour + ", " + condo + ", " + "Nº " + propertyNumber + ", " + city;

        if (condo.equals("") && blockCondo.equals("") && apartment.equals("")) {
            return houseStr;
        } else if (apartment.equals("")) {
            return condominiumStr;
        } else {
            return apartmentStr;
        }
    }

}
