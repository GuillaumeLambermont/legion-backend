package be.corsac.legion.buildings.buildingsDao;

public record CreateBuildingsDTO(

        String playerId,
        int windmillLevel,
        int lumberjackLevel,
        int quarryLevel) {
}
