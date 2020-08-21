package com.sevensense.portalservice.adapter.internal.rest.registration.model;

import com.sevensense.portalservice.domain.entities.user.role.RoleType;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Value;

@Value
public class RegisterRequest {

    @NotNull String username;
    @NotNull String password;
    @NotNull @Email String email;
    @NotNull Set<RoleType> roles;

}
