package com.sevensense.portalservice.domain.entities;

import com.sevensense.portalservice.domain.events.DomainName;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "application_events_store")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BaseEventAuditEntity implements Serializable {

    private static final long serialVersionUID = 7462055269090605195L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emitted_time")
    private LocalDateTime emittedTime;

    private String msg;

    @Enumerated(value = EnumType.STRING)
    private DomainName domain;

    @Column(name = "resource_id")
    private String resourceId;

    public BaseEventAuditEntity(LocalDateTime emittedTime, String msg, DomainName domain, String resourceId) {
        this.emittedTime = emittedTime;
        this.msg = msg;
        this.domain = domain;
        this.resourceId = resourceId;
    }

}
