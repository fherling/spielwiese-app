package de.frankherling.spielwiese.app.application.port.out;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface OutboxPort {


    void addMessage(@NotEmpty String message);

    @Transactional(propagation = Propagation.MANDATORY)
    @Timed
    @Counted
    void addAndPublishMessage(String message);
}
