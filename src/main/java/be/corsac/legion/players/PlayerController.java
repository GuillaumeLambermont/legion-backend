package be.corsac.legion.players;

import be.corsac.legion.players.playersDao.PlayerDTO;
import be.corsac.legion.players.playersDao.PlayerIdDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/players")
@CrossOrigin(origins = "http://localhost:4200")
public class PlayerController {

    private PlayerService playerService;
    private PlayerRepository playerRepository;

    public PlayerController(PlayerService playerService, PlayerRepository playerRepository) {
        this.playerService = playerService;
        this.playerRepository = playerRepository;
    }

    @GetMapping("/profile")
    public Map<String, Object> getUserClaims(@AuthenticationPrincipal Jwt jwt) {
        // 'jwt.getClaims()' contains all information inside the token
        return jwt.getClaims();
    }

    @GetMapping("/me")
    public Player getLoggedInPlayer(@AuthenticationPrincipal Jwt jwt) {
        // 1. Extract data from the Keycloak Token
        String keycloakId = jwt.getSubject(); // The 'sub' claim is the unique ID
        String username = jwt.getClaimAsString("preferred_username");
        String email = jwt.getClaimAsString("email");

        // 2. Check if the player already exists in our local database
        return playerRepository.findById(keycloakId)
                .orElseGet(() -> {
                    // 3. If they don't exist, this is their first time logging in!
                    // Create a new player record and save it to the database.
                    Player newPlayer = new Player(keycloakId, username, email);
                    return playerRepository.save(newPlayer);
                });
    }

    @GetMapping
    @PreAuthorize("hasRole('player')")
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @PreAuthorize("hasRole('player')")
    public PlayerDTO getPlayerById(@PathVariable String id) {
        return playerService.getPlayerById(id);
    }

//    @PreAuthorize("hasRole('player')")
//    @PostMapping(consumes = "application/json", produces = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public PlayerDTO createPlayer(@RequestBody CreatePlayerDTO createPlayerDTO) throws Exception { return playerService.createPlayer(createPlayerDTO); }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public PlayerIdDTO deletePlayer(@PathVariable String id) {
        return playerService.deletePlayer(id); }
}
