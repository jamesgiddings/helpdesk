package com.fdmgroup.helpdeskapi.model.response;

import com.fdmgroup.helpdeskapi.Constants;
import com.fdmgroup.helpdeskapi.model.Engineer;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class EngineerResponse extends UserResponse {
    public EngineerResponse(Engineer engineer) {
        super(engineer);
        this.specialism = engineer.getSpecialism();
        this.userType = Constants.ENGINEER_USER_TYPE;
    }
}
