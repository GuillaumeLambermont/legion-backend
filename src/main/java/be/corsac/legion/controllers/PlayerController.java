package be.corsac.legion.controllers;

import be.corsac.legion.models.player.Player;
import be.corsac.legion.models.player.playerDTOs.CreatePlayerDTO;
import be.corsac.legion.models.player.playerDTOs.PlayerDTO;
import be.corsac.legion.models.player.playerDTOs.PlayerIdDTO;
import be.corsac.legion.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<Player> getAllPlayers() { return playerService.getAllPlayers(); }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Player getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDTO createPlayer(@RequestBody CreatePlayerDTO createPlayerDTO) { return playerService.createPlayer(createPlayerDTO); }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public PlayerIdDTO deletePlayer(@PathVariable Long id) {
        return playerService.deletePlayer(id); }
}
