package com.sevensense.portalservice.domain.jpa.repository.authentication;

import com.sevensense.portalservice.domain.entities.authentication.AuthenticationToken;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Long> {

    Optional<AuthenticationToken> findTopByUserBidOrderByIdDesc(UUID userBid);

    Optional<AuthenticationToken> findTopByJwtOrderByIdDesc(String jwt);

}
