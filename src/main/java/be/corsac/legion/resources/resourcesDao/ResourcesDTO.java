package be.corsac.legion.resources.resourcesDao;

import java.time.LocalDateTime;

public record ResourcesDTO(
        String playerId,
        float foodAmount,
        float woodAmount,
        float stoneAmount,
        LocalDateTime lastUpdated) {
}
