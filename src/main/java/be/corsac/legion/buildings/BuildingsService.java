package be.corsac.legion.buildings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuildingsService {

    @Autowired
    private BuildingsRepository buildingsRepository;

    public Buildings getPlayerBuildings(String playerId) {
        // Build no player data exception
         return buildingsRepository.findByPlayerId(playerId).orElseThrow();
    }


}
