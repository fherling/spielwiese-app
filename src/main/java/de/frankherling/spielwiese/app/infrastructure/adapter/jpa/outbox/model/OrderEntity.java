package de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "order_id")
    private UUID orderId;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "modified_at")
    private OffsetDateTime modefiedAt;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;

    @Column(name = "order_type", nullable = false)
    private String orderType;

    @Version
    @Column(name = "version")
    long version;

}
