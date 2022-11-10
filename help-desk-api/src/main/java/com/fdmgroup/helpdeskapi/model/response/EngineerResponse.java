package com.fdmgroup.helpdeskapi.model.response;

import com.fdmgroup.helpdeskapi.model.Engineer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EngineerResponse extends UserResponse {
    private String specialism;

    public EngineerResponse(Engineer engineer) {
        super(engineer);
        this.specialism = engineer.getSpecialism();
        this.userType = "Engineer";
    }
}
