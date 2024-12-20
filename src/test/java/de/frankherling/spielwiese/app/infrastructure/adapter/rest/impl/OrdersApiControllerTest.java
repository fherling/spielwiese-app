package de.frankherling.spielwiese.app.infrastructure.adapter.rest.impl;

import de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrdersApiControllerTest {

    private OrdersApiController ordersApiController;

    @BeforeEach
    void setUp() {
        ordersApiController = new OrdersApiController();
    }

    @Test
    void testOrdersGet() {
        ResponseEntity<List<Order>> response = ordersApiController.getOrders();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testOrdersOrderIdGet() {
        String orderId = UUID.randomUUID().toString();
        ResponseEntity<Order> response = ordersApiController.getOrderById(orderId);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(orderId, response.getBody().getId());
    }

    @Test
    void testOrdersPost() {
        Order order = new Order();
        order.setItem("item");
        order.setQuantity(1);
        order.setPrice(10.0f);

        ResponseEntity<Order> response = ordersApiController.createOrder(order);
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("item", response.getBody().getItem());
        assertEquals(1, response.getBody().getQuantity());
        assertEquals(10.0f, response.getBody().getPrice());
    }
}