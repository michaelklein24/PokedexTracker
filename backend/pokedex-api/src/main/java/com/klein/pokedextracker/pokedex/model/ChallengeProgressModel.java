package com.klein.pokedextracker.pokedex.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
        name = "pkx_pokedex_entry_challenge_progress",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"challenge_id", "user_id"})
        },
        indexes = {
                @Index(name = "idx_progress_user_challenge", columnList = "user_id, challenge_id")
        }
)
public class ChallengeProgressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "progress_seq")
    @SequenceGenerator(name = "progress_seq", sequenceName = "progress_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "challenge_id", nullable = false)
    private ChallengeModel challenge;

    @Column(name = "progress_quantity", nullable = false)
    private Long progressQuantity = 0L;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted = false;

    @Column(name = "user_id", nullable = false)
    private String userId;
}
