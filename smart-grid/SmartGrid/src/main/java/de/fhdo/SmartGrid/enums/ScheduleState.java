package de.fhdo.SmartGrid.enums;

public enum ScheduleState {
    WAITING_FOR_START,
    WAITING_FOR_PRICE,
    RUNNING,
    FINISHED,
    CANCELLED_BY_USER,
    CANCELLED_BY_EMERGENCY,
    CANCELLED_BY_PRICE,
}
