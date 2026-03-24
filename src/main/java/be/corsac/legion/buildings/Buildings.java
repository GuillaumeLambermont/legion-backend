package be.corsac.legion.buildings;

import be.corsac.legion.players.Player;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "player_buildings")
public class Buildings {

    @Id
    private String playerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "player_id")
    private Player player;

    private int windmillLevel;
    private int lumberjackLevel;
    private int quarryLevel;

    private float foodPerSecond;
    private float woodPerSecond;
    private float stonePerSecond;

    public Buildings(Player player, int windmillLevel, int lumberjackLevel, int quarryLevel) {
        this.player = player;
        this.windmillLevel = 0;
        this.lumberjackLevel = 0;
        this.quarryLevel = 0;
        this.foodPerSecond = 0;
        this.woodPerSecond = 0;
        this.stonePerSecond = 0;
    }
}
