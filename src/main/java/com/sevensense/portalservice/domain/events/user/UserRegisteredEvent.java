package com.sevensense.portalservice.domain.events.user;

import com.sevensense.portalservice.domain.entities.user.User;
import com.sevensense.portalservice.domain.events.DomainEvent;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class UserRegisteredEvent extends DomainEvent {

    private static final long serialVersionUID = -6325048761511220254L;
    private static final String USER_DOMAIN = "User Domain";
    private static final String MSG_FORMAT = "User with mail %s registered";

    public UserRegisteredEvent(User user) {
        super(USER_DOMAIN, String.format(MSG_FORMAT, user.getEmail()),
            user.getBusinessId().getBusinessIdentifier().toString());
    }

}
