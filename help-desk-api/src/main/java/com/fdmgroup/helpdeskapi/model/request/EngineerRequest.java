package com.fdmgroup.helpdeskapi.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class EngineerRequest extends UserRequest {
    private String specialism;
}
