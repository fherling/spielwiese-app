package de.frankherling.spielwiese.app.infrastructure.adapter.jms.order;

import de.frankherling.spielwiese.app.infrastructure.adapter.jms.order.model.CreateOrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(value = "adapter.jms.enabled", havingValue = "true")
public class OrderProducer {

    private final JmsTemplate jmsTemplate;

    public void createOrder(CreateOrderDTO order) {
        log.info("Sending order: {}", order);
        jmsTemplate.convertAndSend("create-order-queue", order);
    }


    @Scheduled(fixedRate = 5000)
    public void scheduleOrder() {
        createOrder(CreateOrderDTO.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(OffsetDateTime.now())
                .build());
    }
}
