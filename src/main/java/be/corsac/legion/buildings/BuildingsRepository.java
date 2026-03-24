package be.corsac.legion.buildings;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuildingsRepository extends JpaRepository<Buildings, Integer> {

    Optional<Buildings> findByPlayerId(String playerId);
}
