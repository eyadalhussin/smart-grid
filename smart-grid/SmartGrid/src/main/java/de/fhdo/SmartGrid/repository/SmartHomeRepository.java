package de.fhdo.SmartGrid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartHomeRepository extends JpaRepository<SmartHomeRepository, Long> {
}
