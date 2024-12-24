package de.frankherling.spielwiese.app.application.service.outbox;

import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
class OutboxScheduler {

    private final OutboxEventRepository repository;
    private final OutboxEventPublisher publisher;


    @Scheduled(fixedDelay = 5000)
    @Transactional(propagation = Propagation.REQUIRED)
    void checkDelayedEvents() {
        log.debug("Checking for delayed events");

        repository.findTop10ByOrderByCreatedAtAsc()
                .forEach(event -> {
                    log.info("Handle delayed event: {}", event);
                    publisher.publish(event);
                    repository.delete(event);
                });
        log.debug("Finished handling delayed events");
    }


}
