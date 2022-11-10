package com.fdmgroup.helpdeskapi.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fdmgroup.helpdeskapi.model.request.ClientRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Client extends User {

    public Client(ClientRequest clientRequest) {
        super();
        this.setUsername(clientRequest.getUsername());
        this.setEmail(clientRequest.getEmail());
        this.setFullName(clientRequest.getFullName());
        this.setPassword(clientRequest.getPassword());

    }

}
