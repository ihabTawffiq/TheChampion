package com.example.theChampion.services;

import com.example.theChampion.data.entities.GroupEntity;

import com.example.theChampion.data.registrationRequests.GroupRegistrationRequest;
import com.example.theChampion.data.responses.ResponseWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GroupService {
    ResponseEntity<ResponseWrapper<GroupEntity>> addGroup(GroupRegistrationRequest groupRegistrationRequest);
    ResponseEntity<ResponseWrapper<List<GroupEntity>>> listAllgroups();

}
