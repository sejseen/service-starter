package com.sevensense.portalservice.registration;

import com.sevensense.portalservice.adapter.internal.rest.registration.model.RegisterRequest;
import com.sevensense.portalservice.domain.entities.BusinessId;
import com.sevensense.portalservice.domain.entities.user.User;
import com.sevensense.portalservice.domain.entities.user.role.RoleType;
import com.sevensense.portalservice.domain.events.user.UserRegisteredEvent;
import com.sevensense.portalservice.domain.jpa.repository.user.UserRepository;
import com.sevensense.portalservice.domain.jpa.repository.user.role.RoleRepository;
import com.sevensense.portalservice.infrastucture.eventsource.EventPublisher;
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
