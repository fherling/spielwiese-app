package de.frankherling.spielwiese.app.application.service.mappers;

import de.frankherling.spielwiese.app.domain.model.Order;
import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.model.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {

    @Mapping(target = "oid", ignore = true)
    OrderEntity toEntity(Order order);

    Order toOrder(OrderEntity result);
}
