package com.ktyma.portalservice.domain.jpa.repository.user.privilege;

import com.ktyma.portalservice.domain.entities.user.privilege.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

}
