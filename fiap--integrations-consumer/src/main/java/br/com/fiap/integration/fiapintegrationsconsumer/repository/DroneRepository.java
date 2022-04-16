package br.com.fiap.integration.fiapintegrationsconsumer.repository;

import br.com.fiap.integration.fiapintegrationsconsumer.domain.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DroneRepository extends JpaRepository<Drone, UUID> {
}
