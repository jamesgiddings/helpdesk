package com.fdmgroup.helpdeskapi.model.response;

import lombok.Data;

@Data
public class UserResponse {
    protected long id;

    protected String username;

    protected String email;

    protected String fullName;

    protected String password;

    protected String userType;
}
