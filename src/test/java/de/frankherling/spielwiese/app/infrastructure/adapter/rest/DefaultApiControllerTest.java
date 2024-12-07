package de.frankherling.spielwiese.app.infrastructure.adapter.rest;

import de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DefaultApiControllerTest {

    private DefaultApiController defaultApiController;

    @BeforeEach
    void setUp() {
        defaultApiController = new DefaultApiController();
    }

    @Test
    void testOrdersGet() {
        ResponseEntity<List<Order>> response = defaultApiController.ordersGet();
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void testOrdersOrderIdGet() {
        String orderId = UUID.randomUUID().toString();
        ResponseEntity<Order> response = defaultApiController.ordersOrderIdGet(orderId);
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

        ResponseEntity<Order> response = defaultApiController.ordersPost(order);
        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("item", response.getBody().getItem());
        assertEquals(1, response.getBody().getQuantity());
        assertEquals(10.0f, response.getBody().getPrice());
    }
}