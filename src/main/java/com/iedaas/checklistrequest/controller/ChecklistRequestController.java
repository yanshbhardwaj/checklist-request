package com.iedaas.checklistrequest.controller;

import com.iedaas.checklistrequest.entity.ChecklistRequest;
import com.iedaas.checklistrequest.services.ChecklistRequestServices;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class ChecklistRequestController {

    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    @Autowired
    ChecklistRequestServices checklistRequestServices;

    @GetMapping("/checklistRequest")
    public List<ChecklistRequest> getRequests(@RequestParam Optional<String> searchText, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size,
                                              @RequestParam Optional<String> order, @RequestParam Optional<String> sort){

        return checklistRequestServices.getChecklistRequest(searchText, page, size, order, sort);
    }

    @GetMapping("/checklistRequest/{uid}")
    public ChecklistRequest getRequestById(@PathVariable String uid){
        return checklistRequestServices.getChecklistRequqestById(uid);
    }

    @PostMapping("/checklistRequest")
    public ChecklistRequest addRequest(HttpServletRequest request, @RequestBody ChecklistRequest checklistRequest){

        String token = request.getHeader(HEADER_STRING);
        String user1 = null;
        if (token != null) {
            // parse the token.
            System.out.println(request.getHeader(HEADER_STRING));

            try {
                user1 = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();

            } catch (Exception e) {
                throw e;
            }
        }
        return checklistRequestServices.addChecklistRequest(user1, checklistRequest);
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
