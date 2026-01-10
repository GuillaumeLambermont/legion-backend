package be.corsac.legion.mappers;

import be.corsac.legion.models.player.Player;
import be.corsac.legion.models.player.playerDTOs.CreatePlayerDTO;
import be.corsac.legion.models.player.playerDTOs.PlayerDTO;
import be.corsac.legion.models.player.playerDTOs.PlayerIdDTO;

public class PlayerMapper {

    public static Player fromDTO(CreatePlayerDTO createPlayerDTO) {
        return new Player(createPlayerDTO.username(), createPlayerDTO.email());
    }

    public static PlayerDTO toDTO(Player player) {
        return new PlayerDTO(player.getId(), player.getUsername(), player.getEmail());
    }

    public static PlayerIdDTO toIdDTO(Long playerId) {
        return new PlayerIdDTO(playerId);
    }
}
