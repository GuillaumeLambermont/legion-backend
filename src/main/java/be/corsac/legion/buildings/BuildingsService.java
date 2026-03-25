package be.corsac.legion.buildings;

import be.corsac.legion.buildings.buildingsDao.BuildingsDTO;
import be.corsac.legion.buildings.buildingsDao.BuildingsMapper;
import org.springframework.stereotype.Service;

@Service
public class BuildingsService {

    private final BuildingsRepository buildingsRepository;

    public BuildingsService(BuildingsRepository buildingsRepository) {
        this.buildingsRepository = buildingsRepository;
    }

    public BuildingsDTO getBuildings(String playerId) {
        // Build no player data exception
        return BuildingsMapper.toDTO(buildingsRepository.findByPlayerId(playerId).orElseThrow());
    }

    public BuildingsDTO createBuildings(String playerId) {
        return BuildingsMapper.toDTO(buildingsRepository.save(new Buildings(playerId)));
    }
}
