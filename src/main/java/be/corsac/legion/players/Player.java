package be.corsac.legion.players;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    public Player() {}
    public Player(String playerId, String username, String email) {
        this.id = playerId;
        this.username = username;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
