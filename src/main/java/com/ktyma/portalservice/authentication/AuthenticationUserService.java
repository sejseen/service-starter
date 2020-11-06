package com.ktyma.portalservice.authentication;

import com.ktyma.portalservice.authentication.model.AuthenticatedUser;
import com.ktyma.portalservice.domain.entities.user.User;
import com.ktyma.portalservice.domain.entities.user.role.Role;
import com.ktyma.portalservice.domain.jpa.repository.user.UserRepository;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthenticationUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public AuthenticatedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Email " + email + " not found"));

        return new AuthenticatedUser(user.getEmail(),
            user.getPassword(), getAuthorities(user), user.getBusinessId());
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles()
            .stream()
            .map(Role::getName)
            .toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(userRoles);
    }

}
