package com.sevensense.portalservice.infrastucture.eventsource;

import com.sevensense.portalservice.domain.jpa.repository.BaseEventAuditRepository;
import com.sevensense.portalservice.infrastucture.eventsource.handler.UserEventsHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventSourceConfig {

    @Bean
    public EventPublisher eventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new EventPublisher(applicationEventPublisher);
    }

    @Bean
    public UserEventsHandler userEventsHandler(BaseEventAuditRepository repository) {
        return new UserEventsHandler(repository);
    }

}
