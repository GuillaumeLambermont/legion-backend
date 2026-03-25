package be.corsac.legion.resources.resourcesDao;

import be.corsac.legion.resources.Resources;

public class ResourcesMapper {

    public static Resources fromDTO(CreateResourcesDTO createResourcesDto) {
        return new Resources(
                createResourcesDto.playerId(),
                createResourcesDto.foodAmount(),
                createResourcesDto.woodAmount(),
                createResourcesDto.stoneAmount()
                );
    }

    public static ResourcesDTO toDTO(Resources resources) {
        return new ResourcesDTO(
                resources.getPlayerId(),
                resources.getFoodAmount(),
                resources.getWoodAmount(),
                resources.getStoneAmount()
        );
    }
}
