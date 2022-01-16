package com.example.theChampion.api.v1;

//import com.example.theChampion.data.entities.GroupEntity;
import com.example.theChampion.data.entities.ParticipantEntity;
import com.example.theChampion.data.registrationRequests.GroupRegistrationRequest;
import com.example.theChampion.data.registrationRequests.ParticipantRegistrationRequest;
import com.example.theChampion.data.responses.ResponseWrapper;
//import com.example.theChampion.services.GroupService;
import com.example.theChampion.services.ParticipantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Api(tags = {"Dataset Initializer API"})
@RestController
@RequestMapping(path = "/dataSet")
public class DataSetInitializerController {
    private Boolean isEmpty ;
    private final ParticipantService participantService;

    public DataSetInitializerController(ParticipantService participantService) {
        this.participantService = participantService;
        isEmpty = participantService.listAllParticipants().getBody().getBody().size()<12;
    }
    @ApiOperation("Initialize All Required Data to Run The Champion")
    @GetMapping(path ="/initialize")
    public ResponseEntity<ResponseWrapper<Object>> initializer(){
        if(isEmpty){
            initializeParticipates();
            isEmpty = false;
            return ResponseEntity.ok(ResponseWrapper
                    .builder()
                    .success(true)
                    .message("12 Participant Created with different data && 2 Groups Different Created && assigned 12 participants to 2 groups  randomly && Every group have 6 participants")
                    .body(true).build());
        }else{
            return ResponseEntity.ok(ResponseWrapper
                    .builder()
                    .success(true)
                    .message("No Data Added , Data Already Initialized Before !!!")
                    .body(true).build());
        }
    }

    public void initializeParticipates(){
        List<String> fristName= new ArrayList<>();
        fristName.add("Ahmed");
        fristName.add("mohamed");
        fristName.add("amr");
        fristName.add("khaled");
        fristName.add("Gamal");
        fristName.add("Abdelrahman");
        fristName.add("Magdy");
        List<String> lastName= new ArrayList<>();
        lastName.add("Galal");
        lastName.add("Mohsen");
        lastName.add("Hany");
        lastName.add("Mosaad");
        lastName.add("Mamdoh");
        lastName.add("Hassan");
        lastName.add("Ali");
        Random r = new Random();
        for(int i = 0 ; i < 12 ; i++){
            participantService.addParticipant(ParticipantRegistrationRequest
                    .builder()
                    .department("IT")
                    .firstName(fristName.get(r.nextInt(7)))
                    .lastName(lastName.get(r.nextInt(7)))
                    .phone("01"+r.nextInt(3)+ r.nextInt(1_000_000_00))
                    .build(),true);

        }

    }
}
