package com.example.theChampion.data.entities;

import lombok.*;
import java.util.List;

import javax.persistence.*;

@Table(name = "groups")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @OneToMany(mappedBy = "group"  ,fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    List<ParticipantEntity> groupParticipates;
}
