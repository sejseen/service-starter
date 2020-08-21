package com.sevensense.portalservice.adapter.internal.rest.registration.model;

import com.sevensense.portalservice.domain.entities.user.User;
import com.sevensense.portalservice.domain.entities.user.role.Role;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Value;

@Value
public class RegisterResponse {

    Long id;
    UUID businessId;
    String username;
    String email;
    Set<String> role;

    public static RegisterResponse of(User user) {
        return new RegisterResponse(user.getId(), user.getBusinessId().getBusinessIdentifier(),
            user.getName(),
            user.getEmail(),
            user.getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toUnmodifiableSet()));
    }

}
