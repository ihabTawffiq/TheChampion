package com.example.theChampion.services.impl;

import com.example.theChampion.data.entities.ParticipantEntity;
import com.example.theChampion.data.registrationRequests.ParticipantRegistrationRequest;
import com.example.theChampion.data.responses.ResponseWrapper;
import com.example.theChampion.helpers.ReporterHandler;
import com.example.theChampion.repositories.ParticipateRepository;
import com.example.theChampion.services.ParticipantService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipateRepository participateRepository;

    public ParticipantServiceImpl(ParticipateRepository participateRepository) {
        this.participateRepository = participateRepository;

    }


    @Override
    public ResponseEntity<ResponseWrapper<ParticipantEntity>> addParticipant(ParticipantRegistrationRequest participantRegistrationRequest,
                                                                             Boolean creatable) {
        ReporterHandler<ParticipantEntity> reporterHandler = new ReporterHandler<>();
        try {
            if(creatable){
                ParticipantEntity participantEntity = ParticipantEntity
                        .builder()
                        .firstName(participantRegistrationRequest.getFirstName())
                        .lastName(participantRegistrationRequest.getLastName())
                        .phone(participantRegistrationRequest.getPhone())
                        .department(participantRegistrationRequest.getDepartment())
                        .firstRound(0)
                        .build();

                participateRepository.save(participantEntity);
                ResponseWrapper<ParticipantEntity> wrapper = new ResponseWrapper<>();
                wrapper.setBody(participantEntity);
                wrapper.setMessage("Success");
                wrapper.setSuccess(true);
                return reporterHandler.reportSuccess(wrapper);
            }else {
                return reporterHandler.reportFailure(ResponseWrapper.builder()
                        .body(null)
                        .message("Cant Add More Participants limit reached there are 12 participants")
                        .success(false)
                        .build());
            }
       }catch (Exception | Error e){
        return reporterHandler.reportFailure(ResponseWrapper.builder()
                    .body(null)
                    .message("Failure , "+e.getMessage())
                    .success(false)
                .build());
       }

    }

    @Override
    public ResponseEntity<ResponseWrapper<ParticipantEntity>> updateParticipant(ParticipantEntity participantEntity) {
        ReporterHandler<ParticipantEntity> reporterHandler = new ReporterHandler<>();
        try {
            participateRepository.save(participantEntity);
            ResponseWrapper<ParticipantEntity> wrapper = new ResponseWrapper<>();
            wrapper.setBody(participantEntity);
            wrapper.setMessage("Success");
            wrapper.setSuccess(true);
            return reporterHandler.reportSuccess(wrapper);
        }catch (Exception | Error e){
            return reporterHandler.reportFailure(ResponseWrapper.builder()
                    .body(null)
                    .message("Failure , "+e.getMessage())
                    .success(false)
                    .build());
        }
    }

    @Override
    public ResponseEntity<ResponseWrapper<List<ParticipantEntity>>> listAllParticipants() {
        ReporterHandler<List<ParticipantEntity>> reporterHandler = new ReporterHandler<>();
        try {
            List<ParticipantEntity> participateEntities = participateRepository.findAll();
            ResponseWrapper<List<ParticipantEntity>> wrapper = new ResponseWrapper<>();
            wrapper.setBody(participateEntities);
            wrapper.setMessage("Success");
            wrapper.setSuccess(true);
            return reporterHandler.reportSuccess(wrapper);
        }catch (Exception | Error e){
            return reporterHandler.reportFailure(ResponseWrapper.builder()
                    .body(null)
                    .message("Failure , "+e.getMessage())
                    .success(false)
                    .build());
        }
    }
}
