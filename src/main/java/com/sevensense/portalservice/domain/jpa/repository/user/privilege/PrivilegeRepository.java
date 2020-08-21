package com.sevensense.portalservice.domain.jpa.repository.user.privilege;

import com.sevensense.portalservice.domain.entities.user.privilege.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

}
