package de.frankherling.spielwiese.app.application.service;

import de.frankherling.spielwiese.app.application.port.in.OrdersPort;
import de.frankherling.spielwiese.app.application.port.out.OutboxPort;
import de.frankherling.spielwiese.app.application.service.mappers.OrderEntityMapper;
import de.frankherling.spielwiese.app.domain.model.Order;
import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.OrdersRepository;
import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.model.OrderEntity;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class OrdersService implements OrdersPort {

    private final OrdersRepository repository;
    private final OrderEntityMapper mapper;
    private final OutboxPort outboxPort;

    @Override
    @Timed
    @Counted
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        // Add dummy data for demonstration
        orders.add(Order.builder().build());
        orders.add(Order.builder().build());
        return orders;
    }

    @Override
    @Timed
    @Counted
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Order getOrderById(@NotEmpty String orderId) {
        return null;
    }

    @Override
    @Timed
    @Counted
    @Transactional(propagation = Propagation.REQUIRED)
    public Order createOrder(@Valid @NotNull Order order) {
//        log.info("Creating order: {}", order);
        OrderEntity result = repository.save(mapper.toEntity(order));

        outboxPort.addMessage("Order created: " + order.getId());
        return mapper.toOrder(result);
    }


}
