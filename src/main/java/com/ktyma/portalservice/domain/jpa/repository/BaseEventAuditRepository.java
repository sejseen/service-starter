package com.ktyma.portalservice.domain.jpa.repository;

import com.ktyma.portalservice.domain.entities.BaseEventAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseEventAuditRepository extends JpaRepository<BaseEventAuditEntity, Long> {

}
