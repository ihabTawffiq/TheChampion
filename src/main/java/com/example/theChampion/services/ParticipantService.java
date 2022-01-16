package com.example.theChampion.services;

import com.example.theChampion.data.entities.ParticipantEntity;
import com.example.theChampion.data.registrationRequests.ParticipantRegistrationRequest;
import com.example.theChampion.data.responses.ResponseWrapper;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ParticipantService {
    ResponseEntity<ResponseWrapper<ParticipantEntity>> addParticipant(ParticipantRegistrationRequest participantRegistrationRequest,Boolean creatable);
    ResponseEntity<ResponseWrapper<ParticipantEntity>> updateParticipant(ParticipantEntity participantEntity);
    ResponseEntity<ResponseWrapper<List<ParticipantEntity>>> listAllParticipants();
}
