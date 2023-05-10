package de.fhdo.SmartGrid.repository;

import de.fhdo.SmartGrid.model.WindTurbine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WindTurbineRepository extends JpaRepository<WindTurbine, Long> {
}
