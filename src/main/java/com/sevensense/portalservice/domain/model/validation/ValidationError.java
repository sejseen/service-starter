package com.sevensense.portalservice.domain.model.validation;

import lombok.Value;

@Value
public class ValidationError {

    String message;
    String field;

}
