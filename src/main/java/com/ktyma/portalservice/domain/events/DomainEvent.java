package com.ktyma.portalservice.domain.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public abstract class DomainEvent extends ApplicationEvent {

    String msg;
    String resourceId;

    public DomainEvent(String domainSource, String msg, String resourceId) {
        super(domainSource);
        this.msg = msg;
        this.resourceId = resourceId;
    }

}
