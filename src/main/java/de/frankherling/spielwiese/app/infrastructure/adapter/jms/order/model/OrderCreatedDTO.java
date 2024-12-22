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
public class OrderCreatedDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1692096220630472706L;

    private String id;
    
    private OffsetDateTime createdAt;

}
