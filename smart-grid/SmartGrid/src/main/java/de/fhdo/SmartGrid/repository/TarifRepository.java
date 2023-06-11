package de.fhdo.SmartGrid.repository;

import de.fhdo.SmartGrid.model.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TarifRepository extends JpaRepository<Tarif, Long> {
    Optional<Tarif> findByActiveTrue();
}
