package de.fhdo.SmartGrid.Repository;

import de.fhdo.SmartGrid.model.SolarPark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolarParkRepository extends JpaRepository<SolarPark, Long> {
}
