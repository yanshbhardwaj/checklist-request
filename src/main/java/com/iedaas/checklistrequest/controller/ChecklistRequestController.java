package com.iedaas.checklistrequest.controller;

import com.iedaas.checklistrequest.entity.ChecklistRequest;
import com.iedaas.checklistrequest.services.ChecklistRequestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ChecklistRequestController {

    @Autowired
    ChecklistRequestServices checklistRequestServices;

    @GetMapping("/checklistRequest")
    public List<ChecklistRequest> getRequests(){
        return checklistRequestServices.getChecklistRequest();
    }

    @PostMapping("/checklistRequest")
    public ChecklistRequest addRequest(@RequestBody ChecklistRequest checklistRequest){
        return checklistRequestServices.addChecklistRequest(checklistRequest);
    }

    @PutMapping("/checklistRequest/{uid}")
    public ChecklistRequest updateRequest(@PathVariable String uid, @RequestBody ChecklistRequest checklistRequest){
        return checklistRequestServices.updateChecklistRequest(uid, checklistRequest);
    }

    @DeleteMapping("/checklistRequest/{uid}")
    public String deleteRequest(@PathVariable String uid){
        checklistRequestServices.deleteChecklistRequest(uid);
        return "Deleted Successfully";
    }
}
