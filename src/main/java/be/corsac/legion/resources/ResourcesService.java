package be.corsac.legion.resources;

import be.corsac.legion.buildings.Buildings;
import be.corsac.legion.buildings.BuildingsService;
import be.corsac.legion.players.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Duration;
import java.util.Optional;

import static java.time.Duration.ofSeconds;
import static java.time.LocalTime.now;

@Service
public class ResourcesService {

    @Autowired
    private ResourcesRepository resourcesRepository;
    @Autowired
    private BuildingsService buildingsService;


    public Resources getPlayerResources(String playerId) throws Exception {

         Optional<Resources> storedResources = resourcesRepository.findResourcesByPlayerId(playerId);
         if (storedResources.isPresent()) {
             return updatePlayerResources(addProductionSinceLastUpdated(storedResources.get()));
             // TODO build exception
         } else { throw new Exception(); }
    }

    private Resources addProductionSinceLastUpdated(Resources resources) {
        long elapsedTime = (Duration.between(now(), resources.getLastUpdated())).getSeconds();
        Buildings production = buildingsService.getPlayerBuildings(resources.getPlayerId());

        resources.addProduction(
                calculateProduction(resources.getFoodAmount(), elapsedTime, (int) production.getFoodPerSecond()),
                calculateProduction(resources.getWoodAmount(), elapsedTime, (int) production.getWoodPerSecond()),
                calculateProduction(resources.getStoneAmount(), elapsedTime, (int) production.getStonePerSecond())
        );
        return resources;
    }

    private Resources updatePlayerResources(Resources resources) {
        return  resourcesRepository.save(resources);
    }

    private float calculateProduction(float oldAmount, long elapsedSeconds, int AmountPerSecond) {
        return oldAmount + (elapsedSeconds * AmountPerSecond);
    }
}
