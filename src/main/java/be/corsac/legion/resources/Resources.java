package be.corsac.legion.resources;

import be.corsac.legion.players.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "resources")
public class Resources {

    @Id
    private String playerId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "player_id")
    private Player player;


    private float foodAmount;

    private float woodAmount;

    private float stoneAmount;

    private LocalDateTime lastUpdated;

    public Resources() {}

    public Resources(String playerId, float foodAmount, float woodAmount, float stoneAmount) {
        this.playerId = playerId;
        this.foodAmount = foodAmount;
        this.woodAmount = woodAmount;
        this.stoneAmount = stoneAmount;
    }

    public Resources(String playerId) {
        this.playerId = playerId;
        this.foodAmount = 0;
        this.woodAmount = 0;
        this.stoneAmount = 0;
    }

    public void addProduction(float foodAmount, float woodAmount, float stoneAmount) {
        this.foodAmount += foodAmount;
        this.woodAmount += woodAmount;
        this.stoneAmount += stoneAmount;
    }

}
