package be.corsac.legion.buildings;

import be.corsac.legion.players.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "player_buildings")
public class Buildings {

    @Id
    private String playerId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "player_id")
    private Player player;

    private int windmillLevel;
    private int lumberjackLevel;
    private int quarryLevel;

    public Buildings(String playerId) {
        this.playerId = playerId;
        this.windmillLevel = 0;
        this.lumberjackLevel = 0;
        this.quarryLevel = 0;
    }

    public Buildings(String playerId, int windmill, int lumberjack, int quarry) {
        this.playerId = playerId;
        this.windmillLevel = windmill;
        this.lumberjackLevel = lumberjack;
        this.quarryLevel = quarry;
    }

    public Buildings() {
    }
}
