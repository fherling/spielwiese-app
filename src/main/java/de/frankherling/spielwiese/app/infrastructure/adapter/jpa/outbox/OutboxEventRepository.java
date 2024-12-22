package de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox;


import de.frankherling.spielwiese.app.infrastructure.adapter.jpa.outbox.model.OutboxEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxEventRepository extends JpaRepository<OutboxEventEntity, Long> {
}
