package de.fhdo.SmartGrid.model;

import jakarta.persistence.Entity;

@Entity
public class SolarPark extends EnergyProducer {

    private double panelEfficiency;
    private double panelArea;

    public SolarPark() {

    }

    @Override
    protected void setCurrentPowerGeneration() {

    }

    public SolarPark(String name, double capacity, double panelEfficiency, double panelArea) {
        super(name, capacity);
        this.panelEfficiency = panelEfficiency;
        this.panelArea = panelArea;
    }

    public double getPanelEfficiency() {
        return panelEfficiency;
    }

    public void setPanelEfficiency(double panelEfficiency) {
        this.panelEfficiency = panelEfficiency;
    }

    public double getPanelArea() {
        return panelArea;
    }

    public void setPanelArea(double panelArea) {
        this.panelArea = panelArea;
    }

    @Override
    public String toString() {
        return "SolarPark{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", capacity=" + getCapacity() +
                ", panelEfficiency=" + panelEfficiency +
                ", panelArea=" + panelArea +
                '}';
    }

}
