package com.ktyma.portalservice.authentication;

import com.ktyma.portalservice.adapter.internal.rest.authentication.model.LoginRequest;
import com.ktyma.portalservice.adapter.internal.rest.authentication.model.LoginResponse;
import com.ktyma.portalservice.authentication.model.AuthenticatedUser;
import com.ktyma.portalservice.domain.events.user.UserLoggedInEvent;
import com.ktyma.portalservice.infrastucture.eventsource.EventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationFacade {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtUtils;
    private final AuthenticationTokenService tokenService;
    private final EventPublisher eventPublisher;

    public LoginResponse authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthenticatedUser principal = (AuthenticatedUser) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(principal);
        tokenService.createNew(principal.getBusinessId(), jwtUtils.getExpirationDateFromJwtToken(jwt), jwt);
        eventPublisher.publish(new UserLoggedInEvent(principal));


        return LoginResponse.of(jwt, principal);
    }

    public void logout(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(null);
            tokenService.expire(((AuthenticatedUser) authentication.getPrincipal()).getBusinessId());
        }
    }

}
