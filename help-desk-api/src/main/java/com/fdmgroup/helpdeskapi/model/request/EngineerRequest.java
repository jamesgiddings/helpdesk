package com.fdmgroup.helpdeskapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EngineerRequest extends UserRequest {
    private String specialism;
}
