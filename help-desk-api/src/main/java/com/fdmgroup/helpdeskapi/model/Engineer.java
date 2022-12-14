package com.fdmgroup.helpdeskapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fdmgroup.helpdeskapi.model.request.EngineerRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Engineer extends User {

	public Engineer(EngineerRequest engineerRequest) {
		super();
		this.setUsername(engineerRequest.getUsername());
		this.setEmail(engineerRequest.getEmail());
		this.setFullName(engineerRequest.getFullName());
		this.setPassword(engineerRequest.getPassword());
		this.setSpecialism(engineerRequest.getSpecialism());
	}

	@Column(name = "specialism", nullable = false)
	private String specialism;

	public String getSpecialism() {
		return specialism;
	}

	public void setSpecialism(String specialism) {
		this.specialism = specialism;
	}

}
