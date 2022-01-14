package com.iedaas.checklistrequest.services;

import com.iedaas.checklistrequest.dao.ChecklistRequestRepository;
import com.iedaas.checklistrequest.entity.ChecklistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ChecklistRequestServices {

    @Autowired
    ChecklistRequestRepository checklistRequestRepository;

    public List<ChecklistRequest> getChecklistRequest(){
        return checklistRequestRepository.findAll();
    }

    public ChecklistRequest addChecklistRequest(ChecklistRequest checklistRequest){
        return checklistRequestRepository.save(checklistRequest);
    }

    public ChecklistRequest updateChecklistRequest(String uid,ChecklistRequest checklistRequest){
        ChecklistRequest checklistRequest1 = checklistRequestRepository.findbyUUID(uid);
        checklistRequest1.setDescription(checklistRequest.getDescription());
        checklistRequest1.setStatusId(checklistRequest.getStatusId());
        checklistRequest1.setTitle(checklistRequest1.getTitle());
        checklistRequest1.setUpdatedDate();
        return checklistRequestRepository.save(checklistRequest1);
    }

    public void deleteChecklistRequest(String uid){
        checklistRequestRepository.delete(checklistRequestRepository.findbyUUID(uid));
    }
}
