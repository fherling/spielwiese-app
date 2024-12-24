package de.frankherling.spielwiese.app.application.service.outbox;

import de.frankherling.spielwiese.app.application.port.out.OutboxPort;
import de.frankherling.spielwiese.app.application.service.UUIDService;
import de.frankherling.spielwiese.app.domain.model.OutboxEvent;
import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.OutboxEventRepository;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxService implements OutboxPort {

    private final OutboxEventRepository repository;
    private final ApplicationEventPublisher eventPublisher;
    private final UUIDService uuidService;
    private final OutboxMapper outboxMapper;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    @Timed
    @Counted
    public void addMessage(String message) {
        save(message);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Timed
    @Counted
    @Override
    public void addAndPublishMessage(String message) {
        eventPublisher.publishEvent(save(message));
    }


    private OutboxEvent save(String message) {
        log.info("Adding message to outbox: {}", message);
        OutboxEvent event = OutboxEvent.builder()
                .aggregateId(uuidService.generateUUID().toString())
                .aggregateType("Order")
                .type("OrderCreated")
                .payload(message)
                .build();
        repository.save(outboxMapper.toEntity(event));
        log.info("Message added to outbox: {}", event);
        return event;
    }

}

