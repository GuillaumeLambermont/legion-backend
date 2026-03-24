package be.corsac.legion.buildings;

import be.corsac.legion.buildings.buildingsDao.BuildingsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/buildings")
public class BuildingsController {

    @Autowired
    private BuildingsService buildingsService;

    @GetMapping("playerId")
    public BuildingsDTO getPlayerBuildings(String playerId) {
        return buildingsMapper.toDTO(buildingsService.getPlayerBuildings(playerId));
    }
}
