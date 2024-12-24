package de.frankherling.spielwiese.app.infrastructure.adapter.jms.order;

import de.frankherling.spielwiese.app.infrastructure.adapter.jms.order.model.OrderCreatedDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(value = "adapter.jms.enabled", havingValue = "true")
public class OrderCreatedConsumer {

    @JmsListener(destination = "order-created-queue", concurrency = "1")
    public void receiveOrderCreated(OrderCreatedDTO message) {
        log.info("Received message: {}", message);
        // Add your message processing logic here

    }
}
