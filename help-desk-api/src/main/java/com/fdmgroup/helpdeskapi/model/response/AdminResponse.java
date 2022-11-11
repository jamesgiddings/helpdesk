package com.fdmgroup.helpdeskapi.model.response;

import com.fdmgroup.helpdeskapi.Constants;
import com.fdmgroup.helpdeskapi.model.Admin;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdminResponse extends UserResponse {

    public AdminResponse(Admin admin) {
        super(admin);
        this.userType = Constants.ADMIN_USER_TYPE;
    }

}
