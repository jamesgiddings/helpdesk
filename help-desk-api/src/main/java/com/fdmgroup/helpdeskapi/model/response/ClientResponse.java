package com.fdmgroup.helpdeskapi.model.response;

import com.fdmgroup.helpdeskapi.model.Client;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClientResponse extends UserResponse {
    public ClientResponse(Client client) {
        super(client);
        this.userType = "Client";
    }
}
