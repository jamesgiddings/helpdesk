package com.fdmgroup.helpdeskapi.model.response;

import com.fdmgroup.helpdeskapi.model.Admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdminResponse extends UserResponse {

    public AdminResponse(Admin admin) {
        this.email = admin.getEmail();
        this.fullName = admin.getFullName();
        this.id = admin.getId();
        this.password = admin.getPassword();
        this.username = admin.getUsername();
        this.userType = "Admin";
    }

}
