package com.ktyma.portalservice.domain.exceptions;

public class TokenExpiredException extends RuntimeException {

    private static final long serialVersionUID = 952041842237949106L;
    private static final String DEFAULT_MSG = "Token expired";

    public TokenExpiredException() {
        super(DEFAULT_MSG);
    }

}
