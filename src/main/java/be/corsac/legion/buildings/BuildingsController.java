package be.corsac.legion.buildings;

import be.corsac.legion.buildings.buildingsDao.BuildingsDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buildings")
public class BuildingsController {

    private final BuildingsService buildingsService;

    public BuildingsController(BuildingsService buildingsService) {
        this.buildingsService = buildingsService;
    }

    @PreAuthorize("hasRole('player')")
    @GetMapping("playerId")
    public BuildingsDTO getBuildings(String playerId) {
        return buildingsService.getBuildings(playerId);
    }
}
