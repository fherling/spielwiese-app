package de.frankherling.spielwiese.app.application.port.in;

import de.frankherling.spielwiese.app.domain.model.Order;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface OrdersPort {
    List<Order> getOrders();

    Order getOrderByOrderId(@NotEmpty UUID orderId);

    Order createOrder(@Valid @NotNull Order order);
}
