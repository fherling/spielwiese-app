package de.frankherling.spielwiese.app.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class Order {
    private String orderId;
    private OffsetDateTime createdAt;
    private OffsetDateTime modefiedAt;
    private String orderStatus;
    private String orderType;
}
