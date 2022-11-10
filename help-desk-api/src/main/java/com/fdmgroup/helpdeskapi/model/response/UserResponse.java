package com.fdmgroup.helpdeskapi.model.response;

import com.fdmgroup.helpdeskapi.model.Engineer;
import com.fdmgroup.helpdeskapi.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    public UserResponse(User user) {
        super();
        this.setUsername(user.getUsername());
        this.setEmail(user.getEmail());
        this.setFullName(user.getFullName());
        this.setPassword(user.getPassword());
    }

    protected long id;

    protected String username;

    protected String email;

    protected String fullName;

    protected String password;

    protected String userType;
}
