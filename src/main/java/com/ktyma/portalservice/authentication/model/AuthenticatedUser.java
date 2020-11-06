package com.ktyma.portalservice.authentication.model;

import com.ktyma.portalservice.domain.entities.BusinessId;
import java.util.Collection;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@EqualsAndHashCode(callSuper = true)
public class AuthenticatedUser extends User {

    @Getter
    private UUID businessId;

    public AuthenticatedUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
        BusinessId businessId) {

        super(username, password, authorities);
        this.businessId = businessId.getBusinessIdentifier();
    }

}
