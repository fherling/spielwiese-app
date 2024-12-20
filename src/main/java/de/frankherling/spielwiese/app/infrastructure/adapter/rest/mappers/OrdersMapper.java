package de.frankherling.spielwiese.app.infrastructure.adapter.rest.mappers;

import de.frankherling.spielwiese.app.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    @Mapping(source = "id", target = "id")
    de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.model.Order toApi(Order order);


    List<de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.model.Order> toApi(List<Order> order);

    @Mapping(source = "id", target = "id")
    Order toDomain(de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.model.Order orderDto);
}
