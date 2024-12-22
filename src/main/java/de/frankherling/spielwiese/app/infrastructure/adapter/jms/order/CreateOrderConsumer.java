package de.frankherling.spielwiese.app.infrastructure.adapter.jms.order;

import de.frankherling.spielwiese.app.application.port.in.OrdersPort;
import de.frankherling.spielwiese.app.domain.model.Order;
import de.frankherling.spielwiese.app.infrastructure.adapter.jms.order.model.CreateOrderDTO;
import de.frankherling.spielwiese.app.infrastructure.adapter.jms.order.model.OrderCreatedDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(value = "adapter.jms.enabled", havingValue = "true")
public class CreateOrderConsumer {
    private final OrdersDTOMapper mapper;

    private final OrdersPort service;

    @JmsListener(destination = "create-order-queue", concurrency = "3-10")
    @SendTo("order-created-queue")
    public OrderCreatedDTO receiveCreateOrder(CreateOrderDTO message) {
        log.info("Received message: {}", message);

        Order order = service.createOrder(mapper.toOrder(message));


        // Add your message processing logic here
        return mapper.toOrderCreatedDTO(order);
    }
}
