package com.fdmgroup.helpdeskapi.model.response;

import com.fdmgroup.helpdeskapi.model.Admin;
import com.fdmgroup.helpdeskapi.model.Client;
import com.fdmgroup.helpdeskapi.model.Engineer;
import com.fdmgroup.helpdeskapi.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    public UserResponse(User user) {
        super();
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setEmail(user.getEmail());
        this.setFullName(user.getFullName());
        this.setPassword(user.getPassword());

        if (user instanceof Engineer) {
            this.userType = "Engineer";
            this.specialism = ((Engineer) user).getSpecialism();
        } else if (user instanceof Client) {
            this.userType = "Client";
        } else if (user instanceof Admin) {
            this.userType = "Admin";
        }

    }

    protected long id;

    protected String username;

    protected String email;

    protected String fullName;

    protected String password;

    protected String userType;

    protected String specialism;
}
