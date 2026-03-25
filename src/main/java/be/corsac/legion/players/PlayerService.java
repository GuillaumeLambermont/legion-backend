package be.corsac.legion.players;

import be.corsac.legion.buildings.BuildingsService;
import be.corsac.legion.players.playersDao.CreatePlayerDTO;
import be.corsac.legion.players.playersDao.PlayerDTO;
import be.corsac.legion.players.playersDao.PlayerIdDTO;
import be.corsac.legion.players.playersDao.PlayerMapper;
import be.corsac.legion.resources.ResourcesService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final BuildingsService buildingsService;
    private final ResourcesService resourcesService;

    public PlayerService(PlayerRepository playerRepository, BuildingsService buildingsService, ResourcesService resourcesService) {
        this.playerRepository = playerRepository;
        this.buildingsService = buildingsService;
        this.resourcesService = resourcesService;
    }

    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream().map(PlayerMapper::toDTO).toList();
    }

    @Transactional
    public PlayerDTO createPlayer(CreatePlayerDTO dto) {
        Player player = PlayerMapper.fromDTO(dto);
        player.initializeNewPlayer();
        return PlayerMapper.toDTO(playerRepository.save(player));
    }

    public PlayerDTO getPlayerById(String id) {
        return PlayerMapper.toDTO(playerRepository.findById(id).orElseThrow());
    }

    public PlayerIdDTO deletePlayer(String id) {
        playerRepository.deleteById(id);
        return PlayerMapper.toIdDTO(id);
    }

    public void syncPlayer(String keycloakId, String username, String email) throws Exception {
        Optional<Player> playerIdDb = playerRepository.findById(keycloakId);
        if (playerIdDb.isEmpty()) {
            CreatePlayerDTO createPlayerDTO = new CreatePlayerDTO(keycloakId, username, email);
            createPlayer(createPlayerDTO);
        }
    }

}
