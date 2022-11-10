package com.fdmgroup.helpdeskapi.model.request;

import lombok.AllArgsConstructor;

public abstract class UserRequest {

    private long id;

    private String username;

    private String email;

    private String fullName;

    private String password;

}
