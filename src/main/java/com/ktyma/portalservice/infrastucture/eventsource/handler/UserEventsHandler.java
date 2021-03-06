package com.ktyma.portalservice.infrastucture.eventsource.handler;

import com.ktyma.portalservice.domain.annotations.DomainEventHandler;
import com.ktyma.portalservice.domain.entities.BaseEventAuditEntity;
import com.ktyma.portalservice.domain.events.DomainName;
import com.ktyma.portalservice.domain.events.user.UserLoggedInEvent;
import com.ktyma.portalservice.domain.events.user.UserRegisteredEvent;
import com.ktyma.portalservice.domain.jpa.repository.BaseEventAuditRepository;
import com.ktyma.portalservice.common.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@Slf4j
@AllArgsConstructor
@DomainEventHandler
public class UserEventsHandler implements EventHandler<UserRegisteredEvent> {

    private final BaseEventAuditRepository repository;

    @Override
    @EventListener
    public void handle(UserRegisteredEvent event) {
        repository.save(buildEntity(event.getTimestamp(), event.getMsg(), event.getResourceId()));
        logEvent(event.getMsg());
    }

    @EventListener
    public void handle(UserLoggedInEvent event) {
        repository.save(buildEntity(event.getTimestamp(), event.getMsg(), event.getResourceId()));
        logEvent(event.getMsg());
    }

    @Override
    public void logEvent(String msg) {
        log.info(msg);
    }

    @Override
    public DomainName getDomain() {
        return DomainName.USERS;
    }

    private BaseEventAuditEntity buildEntity(long timestamp, String msg, String resourceId) {
        return new BaseEventAuditEntity(LocalDateConverter.fromTimestamp(timestamp),
            msg, getDomain(), resourceId);
    }

}
