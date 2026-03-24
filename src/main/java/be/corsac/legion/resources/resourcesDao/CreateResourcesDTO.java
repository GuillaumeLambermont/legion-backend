package be.corsac.legion.resources.resourcesDao;

public record CreateResourcesDTO(
        String playerId,
        float foodAmount,
        float woodAmount,
        float stoneAmount){}
