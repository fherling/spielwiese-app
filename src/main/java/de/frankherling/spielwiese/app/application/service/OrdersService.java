package de.frankherling.spielwiese.app.application.service;

import de.frankherling.spielwiese.app.application.port.in.OrdersPort;
import de.frankherling.spielwiese.app.application.port.out.OutboxPort;
import de.frankherling.spielwiese.app.application.service.mappers.OrderEntityMapper;
import de.frankherling.spielwiese.app.domain.model.Order;
import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.OrdersRepository;
import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.model.OrderEntity;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class OrdersService implements OrdersPort {

    private final OrdersRepository repository;
    private final OrderEntityMapper mapper;
    private final OutboxPort outboxPort;
    private final UUIDService uuidService;

    @Override
    @Timed
    @Counted
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        repository.findAll().forEach(orderEntity -> orders.add(mapper.toOrder(orderEntity)));
        return orders;
    }

    @Override
    @Timed
    @Counted
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Order getOrderByOrderId(@NotEmpty UUID orderId) {
        return mapper.toOrder(repository.findOneByOrderId(orderId).orElseThrow(EntityNotFoundException::new));
    }

    @Override
    @Timed
    @Counted
    @Transactional(propagation = Propagation.REQUIRED)
    public Order createOrder(@Valid @NotNull Order order) {
        log.info("Creating order: {}", order);
        OrderEntity orderEntity = mapper.toEntity(order);
        orderEntity.setOrderStatus("CREATED");
        orderEntity.setOrderType("ORDER");
        orderEntity.setCreatedAt(OffsetDateTime.now());
        orderEntity.setModefiedAt(orderEntity.getModefiedAt());
        OrderEntity result = repository.save(orderEntity);

        outboxPort.addAndPublishMessage("Order created: " + order.getOrderId());
        return mapper.toOrder(result);
    }


}
