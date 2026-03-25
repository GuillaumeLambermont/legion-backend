package be.corsac.legion.players.playersDao;

import be.corsac.legion.players.Player;

public class PlayerMapper {

    public static Player fromDTO(CreatePlayerDTO createPlayerDTO) {
        return new Player(createPlayerDTO.playerId(), createPlayerDTO.username(), createPlayerDTO.email());
    }

    public static PlayerDTO toDTO(Player player) {
        return new PlayerDTO(player.getId(), player.getUsername(), player.getEmail());
    }

    public static PlayerIdDTO toIdDTO(String playerId) {
        return new PlayerIdDTO(playerId);
    }
}
