package com.iedaas.checklistrequest.dao;

import com.iedaas.checklistrequest.entity.ChecklistRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ChecklistRequestRepository extends JpaRepository<ChecklistRequest, Integer> {

    @Query(value = "select * from checklist_request c where c.checklist_request_uid= ?1", nativeQuery = true)
    public ChecklistRequest findbyUUID(String uid);
}
