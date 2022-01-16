package com.example.theChampion.repositories;

import com.example.theChampion.data.entities.GroupEntity;
import com.example.theChampion.data.entities.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ParticipateRepository extends JpaRepository<ParticipantEntity,Long> {
    List<ParticipantEntity> findAllByGroupAndFirstRound(GroupEntity groupEntity,int firstRound);
    List<ParticipantEntity> findAllByFirstRound(int firstRound);
    ParticipantEntity findFirstByOrderByScoreDesc();
}
