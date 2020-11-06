package com.ktyma.portalservice.infrastucture.eventsource;

import com.ktyma.portalservice.domain.annotations.DomainEventPublisher;
import com.ktyma.portalservice.domain.events.DomainEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@AllArgsConstructor
@DomainEventPublisher
public class EventPublisher implements Publisher {

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(DomainEvent event) {
        publisher.publishEvent(event);
    }

}


