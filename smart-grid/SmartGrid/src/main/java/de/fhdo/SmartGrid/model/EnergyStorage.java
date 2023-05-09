package de.fhdo.SmartGrid.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EnergyStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String name;
    private double capacity;
    private double chargeLevel;

    public EnergyStorage() {
    }

    public EnergyStorage(String name, double capacity, double chargeLevel) {
        this.name = name;
        this.capacity = capacity;
        this.chargeLevel = chargeLevel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getChargeLevel() {
        return chargeLevel;
    }

    public void setChargeLevel(double chargeLevel) {
        this.chargeLevel = chargeLevel;
    }

    @Override
    public String toString() {
        return "EnergyStorage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", chargeLevel=" + chargeLevel +
                '}';
    }
}
