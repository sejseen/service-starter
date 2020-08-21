package com.sevensense.portalservice.adapter.internal.rest.authentication.model;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Value
public class LoginResponse {

    String jwtToken;
    String email;
    List<String> roles;

    public static LoginResponse of(String jwt, User principal) {
        return new LoginResponse(jwt, principal.getUsername(), mapRoles(principal));
    }

    private static List<String> mapRoles(User principal) {
        return principal.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
    }

}
