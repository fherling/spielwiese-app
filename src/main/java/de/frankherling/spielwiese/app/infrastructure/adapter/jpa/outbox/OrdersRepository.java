package de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox;


import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findOneByOrderId(UUID orderId);
}
