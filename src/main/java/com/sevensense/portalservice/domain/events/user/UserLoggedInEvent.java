package com.sevensense.portalservice.domain.events.user;

import com.sevensense.portalservice.authentication.model.AuthenticatedUser;
import com.sevensense.portalservice.domain.events.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class UserLoggedInEvent extends DomainEvent {

    private static final long serialVersionUID = -1164558227889792670L;

    private static final String USER_DOMAIN = "User Domain";
    private static final String MSG_FORMAT = "User %s logged in to the system successfully";

    public UserLoggedInEvent(AuthenticatedUser user) {
        super(USER_DOMAIN, String.format(MSG_FORMAT, user.getUsername()),
            user.getBusinessId().toString());
    }

}
