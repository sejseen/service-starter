package com.sevensense.portalservice.infrastucture.eventsource;

import com.sevensense.portalservice.domain.events.DomainEvent;

public interface Publisher {

    void publish(DomainEvent event);

}
