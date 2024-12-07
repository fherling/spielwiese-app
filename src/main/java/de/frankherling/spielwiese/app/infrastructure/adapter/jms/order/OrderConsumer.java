package de.frankherling.spielwiese.app.infrastructure.adapter.jms.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderConsumer {
    @JmsListener(destination = "order-queue")
    public void receiveMessage(String message) {
        log.info("Received message: {}", message);
        // Add your message processing logic here
    }
}
