package de.fhdo.SmartGrid.repository;

import de.fhdo.SmartGrid.enums.ScheduleState;
import de.fhdo.SmartGrid.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findScheduleByScheduleState(ScheduleState scheduleState);
}