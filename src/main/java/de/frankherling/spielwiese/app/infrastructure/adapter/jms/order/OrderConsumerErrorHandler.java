package de.frankherling.spielwiese.app.infrastructure.adapter.jms.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Component
@Slf4j
public class OrderConsumerErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
        log.error("Error handling JMS message", t);
        // Add your custom error handling logic here
    }
}