package de.fhdo.SmartGrid.repository;

import de.fhdo.SmartGrid.model.WindPark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WindTurbineRepository extends JpaRepository<WindPark, Long> {
}
