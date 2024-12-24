package de.frankherling.spielwiese.app.application.service.outbox;

import de.frankherling.spielwiese.app.domain.model.OutboxEvent;
import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.model.OutboxEventEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutboxMapper {
    OutboxEventEntity toEntity(OutboxEvent event);
}
