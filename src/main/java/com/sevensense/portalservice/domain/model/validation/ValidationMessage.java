package com.sevensense.portalservice.domain.model.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ValidationMessage {

    ALREADY_EXISTS("%s already exists in system.");

    @Getter
    private String msgFormat;

}
