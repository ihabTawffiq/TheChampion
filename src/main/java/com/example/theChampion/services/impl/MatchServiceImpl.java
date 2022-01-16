package com.example.theChampion.services.impl;

import com.example.theChampion.data.entities.GroupEntity;
import com.example.theChampion.data.entities.MatchEntity;
import com.example.theChampion.data.entities.ParticipantEntity;
import com.example.theChampion.data.responses.ResponseWrapper;
import com.example.theChampion.helpers.ReporterHandler;
import com.example.theChampion.repositories.GroupRepository;
import com.example.theChampion.repositories.MatchRepository;
import com.example.theChampion.repositories.ParticipateRepository;
import com.example.theChampion.services.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final GroupRepository groupRepository;
    private final ParticipateRepository participateRepository;

    public MatchServiceImpl(MatchRepository matchRepository, GroupRepository groupRepository, ParticipateRepository participateRepository) {
        this.matchRepository = matchRepository;
        this.groupRepository = groupRepository;
        this.participateRepository = participateRepository;
    }

    @Override
    public ResponseEntity<ResponseWrapper<List<MatchEntity>>> startFirstRound() {
        ReporterHandler<List<MatchEntity>> reporterHandler = new ReporterHandler<>();
        try {
            List<MatchEntity> allMatchs = new ArrayList<>();
            List<ParticipantEntity> allPlayers = new ArrayList<>();
            Random r = new Random();
            List<GroupEntity> groupEntityList = groupRepository.findAll();
            for (GroupEntity group : groupEntityList){
                List<ParticipantEntity> groupPlayers = participateRepository.findAllByGroupAndFirstRound(group,0);
                while (!groupPlayers.isEmpty()){
                    ParticipantEntity firstPlayer = groupPlayers.remove(r.nextInt(groupPlayers.size()));
                    ParticipantEntity secondPlayer = groupPlayers.remove(r.nextInt(groupPlayers.size()));
                    firstPlayer.setFirstRound(1);
                    secondPlayer.setFirstRound(1);
                    allPlayers.add(firstPlayer);
                    allPlayers.add(secondPlayer);
                    allMatchs.add(MatchEntity
                            .builder()
                            .firstPlayerId(firstPlayer.getId())
                            .secondPlayerId(secondPlayer.getId())
                            .build());
                }
            }
            matchRepository.saveAll(allMatchs);
            participateRepository.saveAll(allPlayers);
            ResponseWrapper<List<MatchEntity>> wrapper = new ResponseWrapper<>();
            wrapper.setBody(allMatchs);
            wrapper.setMessage("Success , The Number Of Created Matches Is : "+allMatchs.size());
            wrapper.setSuccess(true);
            return reporterHandler.reportSuccess(wrapper);

        }catch (Exception | Error e){
            return reporterHandler.reportFailure(ResponseWrapper
                    .builder()
                    .body(null)
                    .message("Failure , First Round Started Already ")
                    .success(false)
                    .build());
        }
    }

    @Override
    public ResponseEntity<ResponseWrapper<List<MatchEntity>>> startTheLeague() {
        ReporterHandler<List<MatchEntity>> reporterHandler = new ReporterHandler<>();
        try {
            List<ParticipantEntity> firstRoundPlayers = participateRepository.findAllByFirstRound(0);
            if(!firstRoundPlayers.isEmpty()){
                for(ParticipantEntity player1 : firstRoundPlayers){
                    ParticipantEntity currentPlayer = player1;
                    List<ParticipantEntity> otherPlayers = new ArrayList<>();
                    otherPlayers.addAll(firstRoundPlayers);
                    otherPlayers.remove(currentPlayer);
                    for (ParticipantEntity player2 : otherPlayers){
                        MatchEntity currentMatch = new MatchEntity();
                        currentMatch.setFirstPlayerId(player1.getId());
                        currentMatch.setSecondPlayerId(player2.getId());
                        Random r = new Random();
                        if(r.nextInt(2)==0){
                            currentMatch.setWinnerId(player1.getId());
                            currentMatch.setLoserId(player2.getId());
                            player1.setScore(player1.getScore()+1);
                        }else{
                            currentMatch.setWinnerId(player2.getId());
                            currentMatch.setLoserId(player1.getId());
                            player2.setScore(player2.getScore()+1);
                        }
                        player1.setFirstRound(1);
                        player2.setFirstRound(1);
                        participateRepository.save(player1);
                        participateRepository.save(player2);
                        matchRepository.save(currentMatch);
                    }

                }
                List<MatchEntity> allMatchesAfterFinishingFirstRound = matchRepository.findAll();

                ResponseWrapper<List<MatchEntity>> wrapper = new ResponseWrapper<>();
                wrapper.setBody(allMatchesAfterFinishingFirstRound);
                wrapper.setMessage("Success The Number Of Created Matches Is : "+allMatchesAfterFinishingFirstRound.size());
                wrapper.setSuccess(true);
                return reporterHandler.reportSuccess(wrapper);
            }else {
                if(participateRepository.findAll().isEmpty()){
                    return reporterHandler.reportFailure(ResponseWrapper
                            .builder()
                            .body(null)
                            .message("Failure Please Go Call Dataset initializer before calling any API")
                            .success(false)
                            .build());
                }else {
                    return reporterHandler.reportFailure(ResponseWrapper
                            .builder()
                            .body(null)
                            .message("Failure League Finished Already, Now You Can Request To Know Who Is The Champion")
                            .success(false)
                            .build());
                }

            }

        }catch (Exception | Error e){
            return reporterHandler.reportFailure(ResponseWrapper
                    .builder()
                    .body(null)
                    .message("Failure , " + e.getMessage())
                    .success(false)
                    .build());
        }


    }

    @Override
    public ResponseEntity<ResponseWrapper<ParticipantEntity>> findTheChampion() {
        ReporterHandler<ParticipantEntity> reporterHandler = new ReporterHandler<>();
        try {
            if(participateRepository.findAll().isEmpty()){
                return reporterHandler.reportFailure(ResponseWrapper
                        .builder()
                        .body(null)
                        .message("Failure Please Go Call Dataset initializer before calling any API")
                        .success(false)
                        .build());
            }
          ParticipantEntity theChampion = participateRepository.findFirstByOrderByScoreDesc();
          if(theChampion.getScore()==0){
              return reporterHandler.reportSuccess(ResponseWrapper
                      .builder()
                      .body(null)
                      .message("Opps , The League did not start till now ")
                      .success(false)
                      .build());
          }else {
              ResponseWrapper<ParticipantEntity> wrapper = new ResponseWrapper<>();
              wrapper.setBody(theChampion);
              wrapper.setMessage("Success");
              wrapper.setSuccess(true);
              return reporterHandler.reportSuccess(wrapper);
          }
        }catch (Exception | Error e){
          return reporterHandler.reportFailure(ResponseWrapper
                  .builder()
                  .body(null)
                  .message("Failure" + e.getMessage())
                  .success(false)
                  .build());
      }

    }
}
