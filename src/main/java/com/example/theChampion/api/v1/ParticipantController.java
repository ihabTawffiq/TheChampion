package com.example.theChampion.api.v1;

import com.example.theChampion.data.entities.ParticipantEntity;
import com.example.theChampion.data.registrationRequests.ParticipantRegistrationRequest;
import com.example.theChampion.data.responses.ResponseWrapper;
import com.example.theChampion.services.ParticipantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Participant APIs"})
@RestController
@RequestMapping(path = "/participant")
public class ParticipantController {

    private final ParticipantService participantService;

    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @ApiOperation("Add New Participant")
    @PostMapping(path = "/add")
    public ResponseEntity<ResponseWrapper<ParticipantEntity>> addParticipant(@RequestBody ParticipantRegistrationRequest participantRegistrationRequest){
        boolean creatable = listAllParticipants().getBody().getBody().size()<12;
        return participantService.addParticipant(participantRegistrationRequest,creatable);
    }
    @ApiOperation("List All Participants")
    @GetMapping(path = "/list")
    public ResponseEntity<ResponseWrapper<List<ParticipantEntity>>> listAllParticipants(){
        return participantService.listAllParticipants();
    }

    @ApiOperation("Update/Edit Exist Participant")
    @PutMapping(path = "/update")
    public ResponseEntity<ResponseWrapper<ParticipantEntity>> updateParticipant(@RequestBody ParticipantEntity participantEntity){
        return participantService.updateParticipant(participantEntity);
    }
}
