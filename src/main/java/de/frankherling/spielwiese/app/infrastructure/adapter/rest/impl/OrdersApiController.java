package de.frankherling.spielwiese.app.infrastructure.adapter.rest.impl;

import de.frankherling.spielwiese.app.application.port.OrdersPort;
import de.frankherling.spielwiese.app.application.service.OrdersService;
import de.frankherling.spielwiese.app.infrastructure.adapter.rest.mappers.OrdersMapper;
import de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.api.OrdersApi;
import de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrdersApiController implements OrdersApi {

    private final OrdersPort ordersService;
    private final OrdersMapper ordersMapper;

    @Override
    public ResponseEntity<List<Order>> getOrders() {

        return ResponseEntity.ok(ordersMapper.toApi(ordersService.getOrders()));
    }

    @Override
    public ResponseEntity<Order> getOrderById(String orderId) {
        // Add dummy data for demonstration
        Order order = new Order();
        order.setId(orderId);
        return ResponseEntity.ok(order);
    }

    @Override
    public ResponseEntity<Order> createOrder(Order order) {
        // Add dummy data for demonstration
        order.setId(UUID.randomUUID().toString());
        return ResponseEntity.status(201).body(order);
    }
}
