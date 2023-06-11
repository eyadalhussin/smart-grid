package de.fhdo.SmartGrid.repository;

import de.fhdo.SmartGrid.model.EnergyStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface EnergyStorageRepository extends JpaRepository<EnergyStorage, Long> {

    @Query("SELECT SUM(e.chargeLevel) FROM EnergyStorage e")
    Double calculateTotalChargeLevel();
    @Query("SELECT SUM(e.chargeLevel) / SUM(e.capacity) FROM EnergyStorage e")
    Double calculateTotalFillrate();
}
