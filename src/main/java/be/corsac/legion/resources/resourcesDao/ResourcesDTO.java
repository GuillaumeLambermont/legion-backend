package be.corsac.legion.resources.resourcesDao;

public record ResourcesDTO(
        String playerId,
        float foodAmount,
        float woodAmount,
        float stoneAmount) {
}
