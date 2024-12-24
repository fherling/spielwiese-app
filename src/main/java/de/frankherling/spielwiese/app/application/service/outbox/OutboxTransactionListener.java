package de.frankherling.spielwiese.app.application.service.outbox;

import de.frankherling.spielwiese.app.domain.model.OutboxEvent;
import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.OutboxEventRepository;
import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.model.OutboxEventEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
class OutboxTransactionListener {
    private final OutboxEventRepository repository;
    private final OutboxEventPublisher publisher;

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void handleOutboxEvent(OutboxEvent event) {
        Optional<OutboxEventEntity> result = repository.findByAggregateId(event.getAggregateId());
        if (result.isPresent()) {
            log.info("Publish event for aggregateId: {}", event.getAggregateId());
            publisher.publish(result.get());
            repository.delete(result.get());
        } else {
            log.error("Event not found for aggregateId: {}", event.getAggregateId());
        }

    }
}
