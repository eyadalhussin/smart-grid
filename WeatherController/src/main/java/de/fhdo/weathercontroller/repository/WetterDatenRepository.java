package de.fhdo.weathercontroller.repository;

import de.fhdo.weathercontroller.model.WetterDaten;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;

public interface WetterDatenRepository extends JpaRepository<WetterDaten, Instant> {
}