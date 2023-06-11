package de.fhdo.SmartGrid.enums;

public enum EmergencyLevel {
    NONE,
    LOW,
    MEDIUM,
    HIGH;

    public EmergencyLevel next() {
        return values()[(ordinal() + 1) % values().length];
    }

    public EmergencyLevel previous() {
        return values()[(ordinal() - 1) % values().length];
    }

    public EmergencyLevel reset() {
        return NONE;
    }
}
