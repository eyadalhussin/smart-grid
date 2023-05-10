package de.fhdo.weathercontroller.repository;

public interface WetterDatenRepository extends org.springframework.data.jpa.repository.JpaRepository<de.fhdo.weathercontroller.model.WetterDaten, java.time.Instant> {
}