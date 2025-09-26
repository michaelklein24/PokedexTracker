package com.klein.pokedextracker.user.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usr_user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userId;
    @Column(unique = true)
    private String username;
    private String firstName;

    public UserModel(String username, String firstName) {
        this.username = username;
        this.firstName = firstName;
    }

    public UserModel() { }
}
