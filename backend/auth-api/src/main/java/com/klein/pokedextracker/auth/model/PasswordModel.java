package com.klein.pokedextracker.auth.model;

import com.klein.pokedextracker.user.model.UserModel;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "auth_password")
public class PasswordModel {

    @EmbeddedId
    private PasswordId id;

    @Column(nullable = false)
    private String encodedPassword;

    @Column(nullable = false)
    private LocalDate createdDate;

    @Column()
    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false, insertable = false, updatable = false)
    private UserModel user;

}
