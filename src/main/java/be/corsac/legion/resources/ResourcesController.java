package be.corsac.legion.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources")
public class ResourcesController {

    public Resources getPlayerResources(String playerId) {
        return null;
    }
}
