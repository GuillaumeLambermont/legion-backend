package be.corsac.legion.resources.resourcesDao;

import java.time.LocalDateTime;

public record UpdateResourcesDTO(
        String playerId,
        float foodAmount,
        float woodAmount,
        float stoneAmount,
        LocalDateTime lastUpdated
) {
}
