package com.ktyma.portalservice.adapter.internal.rest.registration;

import com.ktyma.portalservice.domain.jpa.repository.user.UserRepository;
import com.ktyma.portalservice.domain.model.validation.ValidationMessage;
import com.ktyma.portalservice.domain.model.validation.ValidationResult;
import com.ktyma.portalservice.adapter.internal.rest.registration.model.RegisterRequest;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class RegistrationControllerValidator {

    private final UserRepository userRepository;

    Optional<ValidationResult> validate(RegisterRequest registerRequest) {
        ValidationResult result = ValidationResult.empty();
        validateEmail(registerRequest, result);

        return result.isOk() ? Optional.empty() : Optional.of(result);
    }

    private void validateEmail(RegisterRequest registerRequest, ValidationResult result) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            result.addError(String.format(ValidationMessage.ALREADY_EXISTS.getMsgFormat(),
                registerRequest.getEmail()), "email");
        }
    }

}
