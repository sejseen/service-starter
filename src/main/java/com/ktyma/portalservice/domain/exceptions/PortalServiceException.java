package com.ktyma.portalservice.domain.exceptions;

public class PortalServiceException extends RuntimeException {

    private static final long serialVersionUID = -3908161695898040843L;

    public PortalServiceException(String message) {
        super(message);
    }

}
