package de.frankherling.spielwiese.app.infrastructure.adapter.jms.order;

import de.frankherling.spielwiese.app.infrastructure.adapter.jms.order.model.CreateOrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.OffsetDateTime;
import java.util.UUID;

//@Component
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
        for (int i = 0; i < 2; i++) {
            createOrder(CreateOrderDTO.builder()
                    .orderId(UUID.randomUUID())
                    .createdAt(OffsetDateTime.now())
                    .build());
        }

    }
}
