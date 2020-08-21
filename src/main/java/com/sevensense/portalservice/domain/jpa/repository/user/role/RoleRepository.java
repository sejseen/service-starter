package com.sevensense.portalservice.domain.jpa.repository.user.role;

import com.sevensense.portalservice.domain.entities.user.role.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
