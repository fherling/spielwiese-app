package de.frankherling.spielwiese.app.application.service.outbox;

import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.model.OutboxEventEntity;
import jakarta.jms.JMSException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.JmsException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxEventPublisher {

    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 500),
            retryFor = {JmsException.class, JMSException.class})
    public void publish(OutboxEventEntity event) {
        log.info("Publishing event: {}", event);
    }
}
