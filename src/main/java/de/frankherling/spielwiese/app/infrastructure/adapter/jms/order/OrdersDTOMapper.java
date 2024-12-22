package de.frankherling.spielwiese.app.infrastructure.adapter.jms.order;

import de.frankherling.spielwiese.app.domain.model.Order;
import de.frankherling.spielwiese.app.infrastructure.adapter.jms.order.model.CreateOrderDTO;
import de.frankherling.spielwiese.app.infrastructure.adapter.jms.order.model.OrderCreatedDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdersDTOMapper {

    OrderCreatedDTO toOrderCreatedDTO(Order order);

    Order toOrder(CreateOrderDTO createOrderDTO);
}
