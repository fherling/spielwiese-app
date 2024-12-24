package de.frankherling.spielwiese.app.infrastructure.adapter.rest.impl;

import de.frankherling.spielwiese.app.application.port.in.OrdersPort;
import de.frankherling.spielwiese.app.infrastructure.adapter.rest.mappers.OrdersMapper;
import de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.api.OrdersApi;
import de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class OrdersApiController implements OrdersApi {

    private final OrdersPort ordersService;
    private final OrdersMapper ordersMapper;

    @Override
    public ResponseEntity<List<Order>> getOrders() {
        List<de.frankherling.spielwiese.app.domain.model.Order> orders = ordersService.getOrders();
        if (CollectionUtils.isEmpty(orders)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ordersMapper.toApi(orders));
    }

    @Override
    public ResponseEntity<Order> getOrderByOrderId(UUID orderId) {
        return ResponseEntity.ok(ordersMapper.toApi(ordersService.getOrderByOrderId(orderId)));
    }

    @Override
    public ResponseEntity<Order> createOrder(Order order) {
        de.frankherling.spielwiese.app.domain.model.Order result = ordersService.createOrder(ordersMapper.toDomain(order));
        return ResponseEntity.status(201).body(ordersMapper.toApi(result));
    }
}
