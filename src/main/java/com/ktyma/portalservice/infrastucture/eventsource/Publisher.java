package com.ktyma.portalservice.infrastucture.eventsource;

import com.ktyma.portalservice.domain.events.DomainEvent;

public interface Publisher {

    void publish(DomainEvent event);

}
