package de.frankherling.spielwiese.app.infrastructure.adapter.jms.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6606386676009434472L;
    private String id;
    private OffsetDateTime createdAt;
}
