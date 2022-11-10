package com.fdmgroup.helpdeskapi.model.response;

import com.fdmgroup.helpdeskapi.model.Client;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientResponse extends UserResponse {
    public ClientResponse(Client client) {
        this.email = client.getEmail();
        this.fullName = client.getFullName();
        this.id = client.getId();
        this.password = client.getPassword();
        this.username = client.getUsername();
        this.userType = "Client";
    }
}
