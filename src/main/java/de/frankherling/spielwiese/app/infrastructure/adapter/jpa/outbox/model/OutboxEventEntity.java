package de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "outbox")
@Getter
@Setter
@ToString
public class OutboxEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "aggregate_type")
    private String aggregateType;

    @Column(nullable = false, name = "aggregate_id")
    private String aggregateId;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String payload;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;


}
