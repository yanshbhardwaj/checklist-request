package com.iedaas.checklistrequest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ownerUid;

    private String ownerName;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private Set<ChecklistRequest> checklistRequestSet = new HashSet<>();

    public Owner() {
    }

    public Owner(String ownerUid, String ownerName) {
        this.ownerUid = ownerUid;
        this.ownerName = ownerName;
    }

    public int getId() {
        return id;
    }

    public String getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(String ownerUid) {
        this.ownerUid = ownerUid;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Set<ChecklistRequest> getChecklistRequestSet() {
        return checklistRequestSet;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", ownerUid='" + ownerUid + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", checklistRequestSet=" + checklistRequestSet +
                '}';
    }
}
