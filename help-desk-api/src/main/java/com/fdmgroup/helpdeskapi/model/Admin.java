package com.fdmgroup.helpdeskapi.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fdmgroup.helpdeskapi.model.request.AdminRequest;

import lombok.Data;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User {
    public Admin(AdminRequest adminRequest) {
        super();
        this.setUsername(adminRequest.getUsername());
        this.setEmail(adminRequest.getEmail());
        this.setFullName(adminRequest.getFullName());
        this.setPassword(adminRequest.getPassword());

    }

}
