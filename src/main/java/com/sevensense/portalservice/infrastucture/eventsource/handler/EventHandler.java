package com.sevensense.portalservice.infrastucture.eventsource.handler;

import com.sevensense.portalservice.domain.events.DomainEvent;
import com.sevensense.portalservice.domain.events.DomainName;

interface EventHandler<T extends DomainEvent> {

    void handle(T event);

    void logEvent(String msg);

    DomainName getDomain();

}
