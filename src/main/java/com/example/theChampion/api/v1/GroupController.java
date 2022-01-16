package com.example.theChampion.api.v1;

import com.example.theChampion.data.entities.GroupEntity;
import com.example.theChampion.data.registrationRequests.GroupRegistrationRequest;
import com.example.theChampion.data.responses.ResponseWrapper;
import com.example.theChampion.services.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Group APIs"})
@RestController
@RequestMapping(path = "/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @ApiOperation("Add New Group")
    @PostMapping(path = "/add")
    public ResponseEntity<ResponseWrapper<GroupEntity>> addGroup(@RequestBody GroupRegistrationRequest groupRegistrationRequest){
        return groupService.addGroup(groupRegistrationRequest);
    }

    @ApiOperation("List All Groups")
    @GetMapping(path = "/list")
    public ResponseEntity<ResponseWrapper<List<GroupEntity>>> listAllGroups(){
        return groupService.listAllgroups();
    }


}
