package com.sevensense.portalservice.authentication;

import com.sevensense.portalservice.domain.entities.authentication.AuthenticationToken;
import com.sevensense.portalservice.domain.exceptions.TokenExpiredException;
import com.sevensense.portalservice.domain.jpa.repository.authentication.AuthenticationTokenRepository;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@AllArgsConstructor
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private static final String HTTP_HEADER_AUTH = "Authorization";
    private static final String HTTP_HEADER_AUTH_BEARER_PREFIX = "Bearer ";

    private final JwtService jwtService;
    private final AuthenticationUserService authUserService;
    private final AuthenticationTokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String jwt = parseJwt(request);
        if (jwt != null && jwtService.validateJwtToken(jwt)) {
            String username = jwtService.getUserNameFromJwtToken(jwt);
            UserDetails userDetails = authUserService.loadUserByUsername(username);
            if (tokenNotExpired(jwt)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new TokenExpiredException();
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean tokenNotExpired(String jwt) {
        Optional<AuthenticationToken> authenticationToken = tokenRepository.findTopByJwtOrderByIdDesc(jwt);
        return authenticationToken.isPresent() && authenticationToken.get().isNotExpired();
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(HTTP_HEADER_AUTH);
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(HTTP_HEADER_AUTH_BEARER_PREFIX)) {
            return headerAuth.substring(7);
        }

        return null;
    }

}
