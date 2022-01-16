package com.example.theChampion.repositories;

import com.example.theChampion.data.entities.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepository extends JpaRepository<MatchEntity,Long> {


    Optional<MatchEntity> findByFirstPlayerIdAndSecondPlayerId(Long firstPlayerId, Long secondPlayerId);
}
