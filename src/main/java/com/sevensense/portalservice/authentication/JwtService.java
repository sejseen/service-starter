package com.sevensense.portalservice.authentication;

import com.sevensense.portalservice.authentication.model.AuthenticatedUser;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtService {

    @Value("${auth.jwt.jwtSecret:jwtTestSecretX}")
    private String jwtSecret;

    @Value("${auth.jwt.jwtExpirationMs:86400000}")
    private int jwtExpirationMs;

    private final JwtClaimService claimService;

    public JwtService(JwtClaimService claimService) {
        this.claimService = claimService;
    }

    public String generateJwtToken(AuthenticatedUser principal) {
        return Jwts.builder()
            .setSubject(principal.getUsername())
            .addClaims(claimService.buildClaims(principal))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }


    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String getUserNameFromJwtToken(String token) {
        return claimService.extractUsername(token);
    }

    public Date getExpirationDateFromJwtToken(String token) {
        return claimService.extractExpirationDate(token);
    }

    public UUID getUserBusinessIdFromJwtToken(String token) {
        return claimService.extractUserBid(token);
    }

}
