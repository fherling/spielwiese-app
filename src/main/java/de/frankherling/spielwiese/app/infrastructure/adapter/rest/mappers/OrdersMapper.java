package de.frankherling.spielwiese.app.infrastructure.adapter.rest.mappers;

import de.frankherling.spielwiese.app.domain.model.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdersMapper {


    de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.model.Order toApi(Order order);


    List<de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.model.Order> toApi(List<Order> order);


    Order toDomain(de.frankherling.spielwiese.app.infrastructure.adapter.rest.order.model.Order orderDto);
}
