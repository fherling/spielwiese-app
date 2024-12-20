package de.frankherling.spielwiese.app.application.service;

import de.frankherling.spielwiese.app.application.port.OrdersPort;
import de.frankherling.spielwiese.app.domain.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService implements OrdersPort {
    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        // Add dummy data for demonstration
        orders.add(Order.builder().build());
        orders.add(Order.builder().build());
        return orders;
    }

    @Override
    public Order getOrderById(String orderId) {
        return null;
    }

    @Override
    public Order createOrder(Order order) {
        return null;
    }
}
