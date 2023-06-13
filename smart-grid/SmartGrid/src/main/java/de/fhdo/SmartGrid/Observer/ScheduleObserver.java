package de.fhdo.SmartGrid.Observer;

import de.fhdo.SmartGrid.enums.ScheduleState;

public interface ScheduleObserver {
    void update(ScheduleState scheduleState);
}
