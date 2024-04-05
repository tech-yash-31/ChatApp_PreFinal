package com.chatapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.entity.Groups;
import com.chatapp.serviceImpl.GroupsServiceImpl;

@RestController
public class GroupsController {

	@Autowired
	GroupsServiceImpl groupsServiceImpl;
	
	@PostMapping("/addGroups")
	public Groups insertGroups(@RequestBody Groups groups) {
		
		return groupsServiceImpl.addGroups(groups);
	}
	
	@GetMapping("/groups")
	public List<Groups> displayAllGroups(){
	return	groupsServiceImpl.getAllGroups();
	}

	@GetMapping("/groups/{groupId}")
	public Optional<Groups> getGroupsById(@PathVariable("groupId") int groupId) {
		
		return groupsServiceImpl.getGroupsById(groupId);
	}
	
	@PostMapping("/users/{userId}/groups/{groupId}")
    public ResponseEntity<?> joinGroup(@PathVariable int userId, @PathVariable int groupId) {
        boolean result = groupsServiceImpl.joinGroup(userId, groupId);

        if (result) {
            return ResponseEntity.ok().body("Joined the Group Successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to join the group");
        }
    }
	
}