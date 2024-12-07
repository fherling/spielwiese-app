package de.frankherling.spielwiese.app.infrastructure.adapter.rest;

import de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.api.DefaultApi;
import de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class DefaultApiController implements DefaultApi {

    @Override
    public ResponseEntity<List<Order>> ordersGet() {
        List<Order> orders = new ArrayList<>();
        // Add dummy data for demonstration
        orders.add(new Order());
        orders.add(new Order());
        return ResponseEntity.ok(orders);
    }

    @Override
    public ResponseEntity<Order> ordersOrderIdGet(String orderId) {
        // Add dummy data for demonstration
        Order order = new Order();
        order.setId(orderId);
        return ResponseEntity.ok(order);
    }

    @Override
    public ResponseEntity<Order> ordersPost(Order order) {
        // Add dummy data for demonstration
        order.setId(UUID.randomUUID().toString());
        return ResponseEntity.status(201).body(order);
    }
}
