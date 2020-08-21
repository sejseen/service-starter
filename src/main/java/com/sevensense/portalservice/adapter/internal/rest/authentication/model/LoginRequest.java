package com.sevensense.portalservice.adapter.internal.rest.authentication.model;

import javax.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class LoginRequest {

    @NotBlank String email;
    @NotBlank String password;

}