package com.example.theChampion.data.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "matches")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_player_id")
    private Long firstPlayerId;

    @Column(name = "second_player_id")
    private Long secondPlayerId;

    @Column(name = "winner_id")
    private Long winnerId;

    @Column(name = "loser_id")
    private Long loserId;
}
