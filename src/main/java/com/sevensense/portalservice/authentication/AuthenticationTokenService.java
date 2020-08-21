package com.sevensense.portalservice.authentication;

import com.sevensense.portalservice.common.LocalDateConverter;
import com.sevensense.portalservice.domain.entities.authentication.AuthenticationToken;
import com.sevensense.portalservice.domain.jpa.repository.authentication.AuthenticationTokenRepository;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class AuthenticationTokenService {

    private final AuthenticationTokenRepository repository;

    void createNew(UUID userBid, Date expirationDate, String jwt) {
        AuthenticationToken authenticationToken = AuthenticationToken.builder()
            .expirationDate(LocalDateConverter.convertToLocalDateTime(expirationDate))
            .jwt(jwt)
            .userBid(userBid)
            .build();

        repository.save(authenticationToken);
    }

    void expire(UUID userBid) {
        repository.findTopByUserBidOrderByIdDesc(userBid).ifPresent(token -> {
            token.expiryNow();
            repository.save(token);
        });
    }

}
