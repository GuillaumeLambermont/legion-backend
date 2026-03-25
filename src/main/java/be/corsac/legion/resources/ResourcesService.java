package be.corsac.legion.resources;

import be.corsac.legion.buildings.Buildings;
import be.corsac.legion.buildings.BuildingsService;
import be.corsac.legion.buildings.buildingsDao.BuildingsMapper;
import be.corsac.legion.resources.resourcesDao.ResourcesDTO;
import be.corsac.legion.resources.resourcesDao.ResourcesMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

import static java.time.LocalTime.now;

@Service
public class ResourcesService {

    private static final float FOOD_BASE_PRODUCTION = 150f;
    private static final float FOOD_MULTIPLIER = 1.35f;
    private static final float WOOD_BASE_PRODUCTION = 150f;
    private static final float WOOD_MULTIPLIER = 1.25f;
    private static final float STONE_BASE_PRODUCTION = 150f;
    private static final float STONE_MULTIPLIER = 1.15f;

    private ResourcesRepository resourcesRepository;
    private BuildingsService buildingsService;

    public ResourcesService(BuildingsService buildingsService, ResourcesRepository resourcesRepository) {
        this.buildingsService = buildingsService;
        this.resourcesRepository = resourcesRepository;
    }

    public ResourcesDTO createResources(String playerId) {
        return ResourcesMapper.toDTO(resourcesRepository.save(new Resources(playerId)));
    }

    public ResourcesDTO getPlayerResources(String playerId) throws Exception {

         Optional<Resources> storedResources = resourcesRepository.findResourcesByPlayerId(playerId);
         if (storedResources.isPresent()) {
             return ResourcesMapper.toDTO(updatePlayerResources(addProductionSinceLastUpdated(storedResources.get())));
             // TODO build exception
         } else { throw new Exception(); }
    }

    private Resources addProductionSinceLastUpdated(Resources resources) {

        long elapsedTime = (Duration.between(now(), resources.getLastUpdated())).getSeconds();
        Buildings buildings = BuildingsMapper.fromDTO(buildingsService.getBuildings(resources.getPlayerId()));
        resources.addProduction(
                calculateProduction(resources.getFoodAmount(), elapsedTime, (int) calculateFoodProduction(buildings.getWindmillLevel())),
                calculateProduction(resources.getWoodAmount(), elapsedTime, (int) calculateWoodProduction(buildings.getLumberjackLevel())),
                calculateProduction(resources.getStoneAmount(), elapsedTime, (int) calculateStoneProduction(buildings.getQuarryLevel()))
        );
        return resources;
    }

    private Resources updatePlayerResources(Resources resources) {
        return  resourcesRepository.save(resources);
    }

    private float calculateProduction(float oldAmount, long elapsedSeconds, int AmountPerSecond) {
        return oldAmount + (elapsedSeconds * AmountPerSecond);
    }

    private float calculateFoodProduction(int windmillLevel) {
        return FOOD_BASE_PRODUCTION * windmillLevel * FOOD_MULTIPLIER;
    }

    private float calculateWoodProduction(int lumberjackLevel) {
        return WOOD_BASE_PRODUCTION * lumberjackLevel * WOOD_MULTIPLIER;
    }

    private float calculateStoneProduction(int quarryLevel) {
        return STONE_BASE_PRODUCTION * quarryLevel * STONE_MULTIPLIER;
    }
}
