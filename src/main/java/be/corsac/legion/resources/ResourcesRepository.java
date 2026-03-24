package be.corsac.legion.resources;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourcesRepository extends JpaRepository<Resources, String> {

    Optional<Resources> findResourcesByPlayerId(String playerId);
}