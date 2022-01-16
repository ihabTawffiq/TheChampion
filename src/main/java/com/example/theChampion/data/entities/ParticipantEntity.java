package com.example.theChampion.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "participants")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String department;

    @Column(unique=true)
    private String phone;

    private int score;

    @Column(name = "first_round" )
    private int firstRound;


}
