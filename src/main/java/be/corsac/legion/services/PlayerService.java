package be.corsac.legion.services;

import be.corsac.legion.mappers.PlayerMapper;
import be.corsac.legion.models.player.Player;
import be.corsac.legion.models.player.playerDTOs.CreatePlayerDTO;
import be.corsac.legion.models.player.playerDTOs.PlayerDTO;
import be.corsac.legion.models.player.playerDTOs.PlayerIdDTO;
import be.corsac.legion.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() { return playerRepository.findAll(); }

    public PlayerDTO createPlayer(CreatePlayerDTO createPlayerDTO) {

        return PlayerMapper.toDTO(playerRepository.save(PlayerMapper.fromDTO(createPlayerDTO)));
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public PlayerIdDTO deletePlayer(Long id) {
        playerRepository.deleteById(id);
        return PlayerMapper.toIdDTO(id);
    }
}
