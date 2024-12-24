package de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox;


import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.model.OutboxEventEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public interface OutboxEventRepository extends JpaRepository<OutboxEventEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<OutboxEventEntity> findByAggregateId(String aggregateId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<OutboxEventEntity> findTop10ByOrderByCreatedAtAsc();
}
