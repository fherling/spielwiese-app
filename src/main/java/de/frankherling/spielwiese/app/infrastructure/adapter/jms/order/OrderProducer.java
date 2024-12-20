package de.frankherling.spielwiese.app.infrastructure.adapter.jms.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private final JmsTemplate jmsTemplate;

    public void sendOrder(String order) {
        log.info("Sending order: {}", order);
        jmsTemplate.convertAndSend("order-queue", order);
    }


    @Scheduled(fixedRate = 5000)
    public void scheduleOrder() {
        sendOrder("order" + System.currentTimeMillis());
    }
}
