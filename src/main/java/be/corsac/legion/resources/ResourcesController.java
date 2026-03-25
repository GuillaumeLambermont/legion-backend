package be.corsac.legion.resources;

import be.corsac.legion.resources.resourcesDao.ResourcesDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources")
public class ResourcesController {

    private ResourcesService resourcesService;

    public ResourcesController(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @PreAuthorize("hasRole('player')")
    @GetMapping("playerId")
    public ResourcesDTO getPlayerResources(@RequestParam(required = true) String playerId) throws Exception {
        return resourcesService.getPlayerResources(playerId);
    }
}
