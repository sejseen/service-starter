package com.sevensense.portalservice.domain.exceptions;


public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6888268347063042327L;
    private static final String DEFAULT_MSG_FORMAT = "Resource not found : %s";

    public <T> ResourceNotFoundException(Class<T> resourceClazz) {
        super(String.format(DEFAULT_MSG_FORMAT, resourceClazz.getName()));
    }

}
