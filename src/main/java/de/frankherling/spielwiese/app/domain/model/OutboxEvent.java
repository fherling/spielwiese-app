package de.frankherling.spielwiese.app.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class OutboxEvent {

    private String aggregateType;

    private String aggregateId;

    private String type;

    private String payload;

    @Builder.Default
    private OffsetDateTime createdAt = OffsetDateTime.now();


}
