package de.fhdo.SmartGrid.repository;

import de.fhdo.SmartGrid.model.ConventionalPowerPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConventionalPowerPlantRepository extends JpaRepository<ConventionalPowerPlant, Long> {
}
