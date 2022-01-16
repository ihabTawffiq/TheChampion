package com.example.theChampion.services;

import com.example.theChampion.data.entities.MatchEntity;
import com.example.theChampion.data.entities.ParticipantEntity;
import com.example.theChampion.data.responses.ResponseWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MatchService {
    ResponseEntity<ResponseWrapper<List<MatchEntity>>> startFirstRound();
    ResponseEntity<ResponseWrapper<List<MatchEntity>>> startTheLeague();
    ResponseEntity<ResponseWrapper<ParticipantEntity>> findTheChampion();
}
