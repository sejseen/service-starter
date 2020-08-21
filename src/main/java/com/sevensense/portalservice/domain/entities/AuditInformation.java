package com.sevensense.portalservice.domain.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
public abstract class AuditInformation {

    @Column(name = "CREATION_DATE", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    protected LocalDateTime created;

    @PrePersist
    private void onCreate() {
        created = LocalDateTime.now();
    }

}