package com.sevensense.portalservice.authentication;

import com.sevensense.portalservice.authentication.model.AuthenticatedUser;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class JwtClaimService {

    private static final String BUSINESS_ID_KEY_NAME = "businessId";

    @Value("${auth.jwt.jwtSecret:jwtTestSecretX}")
    private String jwtSecret;

    Map<String, Object> buildClaims(AuthenticatedUser authenticatedUser) {
        return new HashMap<>(Map.of(BUSINESS_ID_KEY_NAME, authenticatedUser.getBusinessId()));
    }

    String extractUsername(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    Date extractExpirationDate(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getExpiration();
    }

    UUID extractUserBid(String token) {
        String bid = (String) Jwts.parser().setSigningKey(jwtSecret)
            .parseClaimsJws(token).getBody()
            .get(BUSINESS_ID_KEY_NAME);
        return UUID.fromString(bid);
    }

}
