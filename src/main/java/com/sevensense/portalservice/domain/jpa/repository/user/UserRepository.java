package com.sevensense.portalservice.domain.jpa.repository.user;

import com.sevensense.portalservice.domain.entities.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
