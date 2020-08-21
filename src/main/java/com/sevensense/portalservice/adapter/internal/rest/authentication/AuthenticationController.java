package com.sevensense.portalservice.adapter.internal.rest.authentication;

import com.sevensense.portalservice.adapter.internal.rest.authentication.model.LoginRequest;
import com.sevensense.portalservice.adapter.internal.rest.authentication.model.LoginResponse;
import com.sevensense.portalservice.authentication.AuthenticationFacade;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Authentication", tags = "Auth API")
@AllArgsConstructor
@RestController
public class AuthenticationController {

    private final AuthenticationFacade facade;

    @PostMapping("/auth/signin")
    public ResponseEntity<? extends LoginResponse> authenticateUser(@RequestBody @Validated LoginRequest loginRequest) {
        return ResponseEntity.ok().body(facade.authenticate(loginRequest));
    }

    @PreAuthorize(value = "isAuthenticated()")
    @PostMapping("/auth/signout")
    public ResponseEntity<?> logoutUser(Authentication authentication) {
        facade.logout(authentication);
        return ResponseEntity.ok().build();
    }

}