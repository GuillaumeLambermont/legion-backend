package be.corsac.legion.players;

import be.corsac.legion.players.playersDao.CreatePlayerDTO;
import be.corsac.legion.players.playersDao.PlayerDTO;
import be.corsac.legion.players.playersDao.PlayerIdDTO;
import be.corsac.legion.players.playersDao.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() { return playerRepository.findAll(); }

    public PlayerDTO createPlayer(CreatePlayerDTO createPlayerDTO) {

        return PlayerMapper.toDTO(playerRepository.save(PlayerMapper.fromDTO(createPlayerDTO)));
    }

    public Player getPlayerById(String id) {
        return playerRepository.findById(id).orElse(null);
    }

    public PlayerIdDTO deletePlayer(String id) {
        playerRepository.deleteById(id);
        return PlayerMapper.toIdDTO(id);
    }

    public void syncPlayer(String keycloakId, String username, String email) {
        Optional<Player> playerIdDb = playerRepository.findById(keycloakId);
        if (playerIdDb.isEmpty()) {
            playerRepository.save(new Player(keycloakId, username, email));

        }
    }
}
