package com.ktyma.portalservice.registration;

import com.ktyma.portalservice.adapter.internal.rest.registration.model.RegisterRequest;
import com.ktyma.portalservice.domain.entities.BusinessId;
import com.ktyma.portalservice.domain.entities.user.User;
import com.ktyma.portalservice.domain.entities.user.role.RoleType;
import com.ktyma.portalservice.domain.events.user.UserRegisteredEvent;
import com.ktyma.portalservice.domain.jpa.repository.user.UserRepository;
import com.ktyma.portalservice.domain.jpa.repository.user.role.RoleRepository;
import com.ktyma.portalservice.infrastucture.eventsource.EventPublisher;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationCommandService {

    private final EventPublisher eventPublisher;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public User register(RegisterRequest request) {
        User user = createUser(request);
        addRoles(user, request.getRoles());
        userRepository.save(user);
        eventPublisher.publish(new UserRegisteredEvent(user));

        return user;
    }

    private User createUser(RegisterRequest request) {
        return User.builder()
            .businessId(BusinessId.withRandomUUID())
            .email(request.getEmail())
            .password(encoder.encode(request.getPassword()))
            .name(request.getUsername())
            .build();
    }

    private void addRoles(User user, Set<RoleType> roles) {
        roles.forEach(roleType -> roleRepository.findByName(roleType.name())
            .ifPresent(user::addRole));
    }

}
