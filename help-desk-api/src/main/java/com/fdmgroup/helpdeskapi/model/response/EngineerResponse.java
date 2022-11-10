package com.fdmgroup.helpdeskapi.model.response;

import com.fdmgroup.helpdeskapi.model.Engineer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EngineerResponse extends UserResponse {
    private String specialism;

    public EngineerResponse(Engineer engineer) {
        this.email = engineer.getEmail();
        this.fullName = engineer.getFullName();
        this.id = engineer.getId();
        this.password = engineer.getPassword();
        this.username = engineer.getUsername();
        this.specialism = engineer.getSpecialism();
        this.userType = "Engineer";
    }
}
