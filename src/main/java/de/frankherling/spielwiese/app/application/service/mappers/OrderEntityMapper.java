package de.frankherling.spielwiese.app.application.service.mappers;

import de.frankherling.spielwiese.app.domain.model.Order;
import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.model.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {


    OrderEntity toEntity(Order order);

    Order toOrder(OrderEntity result);
}
