package de.fhdo.SmartGrid.repository;

import de.fhdo.SmartGrid.model.EnergyStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EnergyStorageRepository extends JpaRepository<EnergyStorage, Long> {
}