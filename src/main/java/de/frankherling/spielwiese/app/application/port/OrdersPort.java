package de.frankherling.spielwiese.app.application.port;

import de.frankherling.spielwiese.app.domain.model.Order;

import java.util.List;

public interface OrdersPort {
    List<Order> getOrders();
    Order getOrderById(String orderId);
    Order createOrder(Order order);
}
