package com.example.theChampion.services.impl;

import com.example.theChampion.data.entities.GroupEntity;
import com.example.theChampion.data.registrationRequests.GroupRegistrationRequest;
import com.example.theChampion.data.responses.ResponseWrapper;
import com.example.theChampion.helpers.ReporterHandler;
import com.example.theChampion.repositories.GroupRepository;
import com.example.theChampion.services.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public ResponseEntity<ResponseWrapper<GroupEntity>> addGroup(GroupRegistrationRequest groupRegistrationRequest) {
        ReporterHandler<GroupEntity> reporterHandler = new ReporterHandler<>();
        try {
            GroupEntity groupEntity = GroupEntity
                    .builder()
                        .groupName(groupRegistrationRequest.getGroupName())
                    .build();
            groupRepository.save(groupEntity);
            ResponseWrapper<GroupEntity> wrapper = new ResponseWrapper<>();
            wrapper.setBody(groupEntity);
            wrapper.setMessage("Success");
            wrapper.setSuccess(true);
            return reporterHandler.reportSuccess(wrapper);
        }catch (Exception | Error e){
            return reporterHandler.reportFailure(ResponseWrapper
                    .builder()
                        .body(null)
                        .message("Failure , "+e.getMessage())
                        .success(false)
                    .build());
        }

    }

    @Override
    public ResponseEntity<ResponseWrapper<List<GroupEntity>>> listAllgroups() {
        ReporterHandler<List<GroupEntity>> reporterHandler = new ReporterHandler<>();
        try {
            List<GroupEntity> groupEntities = groupRepository.findAll();
            ResponseWrapper<List<GroupEntity>> wrapper = new ResponseWrapper<>();
            wrapper.setBody(groupEntities);
            wrapper.setMessage("Success");
            wrapper.setSuccess(true);
            return reporterHandler.reportSuccess(wrapper);
        }catch (Exception | Error e){
            return reporterHandler.reportFailure(ResponseWrapper
                    .builder()
                        .body(null)
                        .message("Failure , "+e.getMessage())
                        .success(false)
                    .build());
        }
    }
}
