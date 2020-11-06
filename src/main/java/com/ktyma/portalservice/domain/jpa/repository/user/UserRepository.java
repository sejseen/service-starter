package com.ktyma.portalservice.domain.jpa.repository.user;

import com.ktyma.portalservice.domain.entities.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
