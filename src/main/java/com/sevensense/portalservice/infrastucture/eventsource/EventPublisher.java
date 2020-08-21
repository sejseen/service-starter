package com.sevensense.portalservice.infrastucture.eventsource;

import com.sevensense.portalservice.domain.annotations.DomainEventPublisher;
import com.sevensense.portalservice.domain.events.DomainEvent;
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


