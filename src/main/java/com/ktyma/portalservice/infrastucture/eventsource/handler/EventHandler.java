package com.ktyma.portalservice.infrastucture.eventsource.handler;

import com.ktyma.portalservice.domain.events.DomainEvent;
import com.ktyma.portalservice.domain.events.DomainName;

interface EventHandler<T extends DomainEvent> {

    void handle(T event);

    void logEvent(String msg);

    DomainName getDomain();

}
