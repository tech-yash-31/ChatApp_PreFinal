package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.Groups;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Integer> {

}
