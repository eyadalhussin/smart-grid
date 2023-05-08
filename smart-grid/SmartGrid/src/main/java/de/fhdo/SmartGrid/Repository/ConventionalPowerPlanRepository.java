package de.fhdo.SmartGrid.Repository;

import de.fhdo.SmartGrid.model.ConventionalPowerPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConventionalPowerPlanRepository extends JpaRepository<ConventionalPowerPlant, Long> {
}
