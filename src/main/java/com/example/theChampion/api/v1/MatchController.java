package com.example.theChampion.api.v1;

import com.example.theChampion.data.entities.GroupEntity;
import com.example.theChampion.data.entities.MatchEntity;
import com.example.theChampion.data.entities.ParticipantEntity;
import com.example.theChampion.data.responses.ResponseWrapper;
import com.example.theChampion.services.MatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/match")
@Api(tags = {"Play League Matches APIs"})
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @ApiOperation("Start the league And List All Matches With All Details")
    @GetMapping(path = "/startTheLeague")
    public ResponseEntity<ResponseWrapper<List<MatchEntity>>> startTheLeague(){
        return matchService.startTheLeague();
    }

    @ApiOperation("Find The Champion")
    @GetMapping(path = "/findTheChampion")
    public ResponseEntity<ResponseWrapper<ParticipantEntity>> findTheChampion(){
        return matchService.findTheChampion();
    }


}
