package com.ktyma.portalservice.adapter.internal.rest.registration;

import com.ktyma.portalservice.domain.entities.user.User;
import com.ktyma.portalservice.adapter.internal.rest.registration.model.RegisterRequest;
import com.ktyma.portalservice.adapter.internal.rest.registration.model.RegisterResponse;
import com.ktyma.portalservice.domain.exceptions.ValidationException;
import com.ktyma.portalservice.registration.RegistrationCommandService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Registration", tags = "Auth API")
@AllArgsConstructor
@RestController
public class RegistrationController {

    private final RegistrationCommandService commandService;
    private final RegistrationControllerValidator validator;

    //    @PreAuthorize(value = "hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/auth/signup")
    public ResponseEntity<? extends RegisterResponse> registerUser(
        @RequestBody @Validated RegisterRequest registerRequest) {
        validator.validate(registerRequest).ifPresent(errors -> {
            throw new ValidationException(errors.getErrors());
        });

        User createdUser = commandService.register(registerRequest);
        return ResponseEntity.ok(RegisterResponse.of(createdUser));
    }

}
