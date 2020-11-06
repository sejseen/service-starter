package com.ktyma.portalservice.domain.exceptions;

import com.ktyma.portalservice.domain.model.validation.ValidationError;
import java.util.List;
import lombok.Getter;

public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 5989611600667104607L;
    private static final String ERROR_MSG = "Validation errors occurred";

    @Getter
    private final transient List<ValidationError> errors;

    public ValidationException(List<ValidationError> errors) {
        super(ERROR_MSG);
        this.errors = errors;
    }

}
