package com.iedaas.checklistrequest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "checklist_request")
public class ChecklistRequest {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "checklist_request_uid")
    @Type(type= "uuid-char")
    private UUID checklistRequestUid;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "status_id")
    private int statusId=1;

    @Column(name = "created_date")
    private Timestamp createdDate=Timestamp.valueOf(LocalDateTime.now());

    @Column(name = "updated_date")
    private Timestamp updatedDate=Timestamp.valueOf(LocalDateTime.now());

//    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ownerId", referencedColumnName = "Id")
    private Owner owner;

    public ChecklistRequest() {
    }

    public ChecklistRequest(UUID checklistRequestUid, String description, String title,
                            int statusId, Timestamp createdDate, Timestamp updatedDate, Owner owner) {
        this.checklistRequestUid = checklistRequestUid;
        this.description = description;
        this.title = title;
        this.statusId = statusId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.owner = owner;
    }

    public UUID getChecklistRequestUid() {
        return checklistRequestUid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate() {
        this.updatedDate = Timestamp.valueOf(LocalDateTime.now());
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "ChecklistRequest{" +
                "checklistRequestUid=" + checklistRequestUid +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", statusId=" + statusId +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", owner=" + owner +
                '}';
    }
}
