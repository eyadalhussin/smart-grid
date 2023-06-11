package de.fhdo.SmartGrid.model;

import jakarta.persistence.*;

import java.time.Instant;

public class Tarif {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String name;
    private double price;

    private Instant validFrom;

    public Instant getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Instant validFrom) {
        this.validFrom = validFrom;
    }

    public Instant getValidTo() {
        return validTo;
    }

    public void setValidTo(Instant validTo) {
        this.validTo = validTo;
    }

    private Instant validTo;

    private boolean active;

    public Tarif(String name, double price, Instant validFrom, Instant validTo, boolean active) {
        this.name = name;
        this.price = price;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.active = active;
    }

    public Tarif() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
