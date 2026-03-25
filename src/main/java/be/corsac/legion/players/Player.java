package be.corsac.legion.players;

import be.corsac.legion.buildings.Buildings;
import be.corsac.legion.resources.Resources;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "players")
public class Player {

    @Id
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Buildings buildings;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Resources resources;

    public Player() {}
    public Player(String playerId, String username, String email) {
        this.id = playerId;
        this.username = username;
        this.email = email;
    }

    // Inside Player.java
    public void initializeNewPlayer() {
        this.buildings = new Buildings();
        this.buildings.setPlayer(this);

        this.resources = new Resources();
        this.resources.setPlayer(this);
    }
}
