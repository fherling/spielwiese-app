package de.frankherling.spielwiese.app.application.service;

import de.frankherling.spielwiese.app.application.port.out.OutboxPort;
import de.frankherling.spielwiese.app.application.service.mappers.OrderEntityMapper;
import de.frankherling.spielwiese.app.domain.model.Order;
import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.OrdersRepository;
import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.model.OrderEntity;
import org.instancio.Instancio;
import org.instancio.Select;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrdersServiceTest {

    @InjectMocks
    OrdersService cut;

    @Mock
    OrdersRepository ordersRepository;

    @Spy
    OrderEntityMapper orderEntityMapper = Mappers.getMapper(OrderEntityMapper.class);

    @Mock
    OutboxPort outboxPort;


    @Test
    void getOrders() {
        List<Order> result = cut.getOrders();
        assertNotNull(result);

        verify(ordersRepository, times(1)).findAll();
    }

    @Test
    void getOrderByOrderId() {
    }

    @Test
    void createOrder() {

        Order order = Instancio.of(Order.class)
                .set(Select.field(Order::getOrderId), UUID.randomUUID().toString())
                .create();

        OrderEntity entity = Instancio.of(OrderEntity.class).create();

        when(ordersRepository.save(any())).thenReturn(entity);

        Order result = cut.createOrder(order);

        assertNotNull(result);

        verify(outboxPort).addAndPublishMessage(any());

    }
}