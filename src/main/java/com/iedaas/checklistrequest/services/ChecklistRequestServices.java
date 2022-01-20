package com.iedaas.checklistrequest.services;

import com.iedaas.checklistrequest.dao.ChecklistRequestRepository;
import com.iedaas.checklistrequest.dao.OwnerRepository;
import com.iedaas.checklistrequest.entity.ChecklistRequest;
import com.iedaas.checklistrequest.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ChecklistRequestServices {

    @Autowired
    ChecklistRequestRepository checklistRequestRepository;

    @Autowired
    OwnerRepository ownerRepository;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ChecklistRequestServices(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ChecklistRequest> getChecklistRequest(Optional<String> searchText, Optional<Integer> page, Optional<Integer> size,
                                                      @RequestParam Optional<String> order,
                                                      Optional<String> sort){
        Page<ChecklistRequest> result = checklistRequestRepository.findAll(PageRequest.of(
                page.orElse(0), size.orElse(Integer.MAX_VALUE),
                Sort.Direction.valueOf(order.orElse("ASC")), sort.orElse("checklistRequestUid")));
        List<ChecklistRequest> request = new ArrayList<>();
        if(searchText.isPresent()) {
            for (ChecklistRequest checklistRequest : result.getContent()) {
                String text = searchText.get();
                if (checklistRequest.getTitle().contains(text) || checklistRequest.getDescription().contains(text)) {
                    request.add(checklistRequest);
                }
            }
            return request;
        }
        return result.getContent();
    }

    public ChecklistRequest getChecklistRequqestById(String uid){
        return checklistRequestRepository.findbyUUID(uid);
    }

    public ChecklistRequest addChecklistRequest(String user1, ChecklistRequest checklistRequest){

        System.out.println(user1);
        String sql = "select user_uid as owner_uid, concat(first_name, ' ', last_name) as owner_name from user where user_uid='"+user1+"'";
        List<Owner> query = jdbcTemplate.query(sql, (resultSet, i) -> new Owner(resultSet.getString(1), resultSet.getString(2)));
        if(ownerRepository.findbyUUID(user1)==null) {
            Owner owner = new Owner(query.get(0).getOwnerUid(), query.get(0).getOwnerName());
            checklistRequest.setOwner(owner);
        }
        else{
            checklistRequest.setOwner(ownerRepository.findbyUUID(user1));
        }
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
