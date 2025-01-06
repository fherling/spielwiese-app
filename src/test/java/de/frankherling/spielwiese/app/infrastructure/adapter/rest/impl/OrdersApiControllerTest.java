package de.frankherling.spielwiese.app.infrastructure.adapter.rest.impl;

import de.frankherling.spielwiese.app.application.port.in.OrdersPort;
import de.frankherling.spielwiese.app.infrastructure.adapter.rest.mappers.OrdersMapper;
import de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrdersApiControllerTest {

    @InjectMocks
    OrdersApiController cut;

    @Mock
    OrdersPort ordersService;

    @Spy
    OrdersMapper ordersMapper = Mappers.getMapper(OrdersMapper.class);


    @Test
    void testOrdersGet() {
        when(ordersService.getOrders()).thenReturn(List.of(de.frankherling.spielwiese.app.domain.model.Order.builder().build(), de.frankherling.spielwiese.app.domain.model.Order.builder().build()));

        ResponseEntity<List<Order>> response = cut.getOrders();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testOrdersOrderIdGet() {

        UUID orderId = UUID.randomUUID();
        when(ordersService.getOrderByOrderId(any())).thenReturn(de.frankherling.spielwiese.app.domain.model.Order.builder().orderId(orderId.toString()).build());

        ResponseEntity<Order> response = cut.getOrderByOrderId(orderId);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(orderId, response.getBody().getOrderId());
    }

    @Test
    void testOrdersPost() {

        when(ordersService.createOrder(any())).thenReturn(de.frankherling.spielwiese.app.domain.model.Order.builder().orderId(UUID.randomUUID().toString()).build());


        Order order = new Order();
        order.setOrderId(UUID.randomUUID());
        order.setOrderStatus(Order.OrderStatusEnum.PLACED);
        order.setOrderType(Order.OrderTypeEnum.FREE);


        ResponseEntity<Order> response = cut.createOrder(order);
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getOrderId());

    }
}