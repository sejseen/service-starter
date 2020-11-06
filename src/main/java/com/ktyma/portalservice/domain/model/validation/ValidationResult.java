package com.ktyma.portalservice.domain.model.validation;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationResult {

    @Getter
    private List<ValidationError> errors;

    public static ValidationResult empty() {
        return new ValidationResult(new ArrayList<>());
    }

    public void addError(String msg, String field) {
        errors.add(new ValidationError(msg, field));
    }

    public boolean isOk() {
        return errors.isEmpty();
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

}
