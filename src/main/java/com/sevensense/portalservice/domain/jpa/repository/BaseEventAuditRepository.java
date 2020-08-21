package com.sevensense.portalservice.domain.jpa.repository;

import com.sevensense.portalservice.domain.entities.BaseEventAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseEventAuditRepository extends JpaRepository<BaseEventAuditEntity, Long> {

}
