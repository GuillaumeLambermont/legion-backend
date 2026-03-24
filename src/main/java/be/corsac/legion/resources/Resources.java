package be.corsac.legion.resources;

import be.corsac.legion.players.Player;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "resources")
public class Resources {

    @Id
    private String playerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "player_id")
    private Player player;


    private float foodAmount;

    private float woodAmount;

    private float stoneAmount;

    private LocalDateTime lastUpdated;

    public Resources() {}

    public Resources(Player player, float foodAmount, float woodAmount, float stoneAmount) {
        this.player = player;
        this.foodAmount = foodAmount;
        this.woodAmount = woodAmount;
        this.stoneAmount = stoneAmount;
    }

    public void addProduction(float foodAmount, float woodAmount, float stoneAmount) {
        this.foodAmount += foodAmount;
        this.woodAmount += woodAmount;
        this.stoneAmount += stoneAmount;
    }

}
