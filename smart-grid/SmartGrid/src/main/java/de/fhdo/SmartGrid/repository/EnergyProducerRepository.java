package de.fhdo.SmartGrid.repository;

import de.fhdo.SmartGrid.model.EnergyProducer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyProducerRepository extends JpaRepository<EnergyProducer, Long> {
}
