package be.corsac.legion.buildings.buildingsDao;

import be.corsac.legion.buildings.Buildings;

public class BuildingsMapper {

    public static BuildingsDTO toDTO(Buildings buildings) {
        return new BuildingsDTO(
                buildings.getPlayerId(),
                buildings.getWindmillLevel(),
                buildings.getLumberjackLevel(),
                buildings.getQuarryLevel()
        );
    }

    public static Buildings fromDTO(CreateBuildingsDTO createBuildingsDTO) {
        return new Buildings(
                createBuildingsDTO.playerId()
        );
    }

    public static Buildings fromDTO(BuildingsDTO buildingsDTO) {
        return new Buildings(
                buildingsDTO.playerId(),
                buildingsDTO.windmillLevel(),
                buildingsDTO.lumberjackLevel(),
                buildingsDTO.quarryLevel()
        );
    }
}
