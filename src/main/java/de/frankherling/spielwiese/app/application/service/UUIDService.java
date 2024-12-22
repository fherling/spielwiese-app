package de.frankherling.spielwiese.app.application.service;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedEpochGenerator;
import de.frankherling.spielwiese.app.application.port.utils.UUIDPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UUIDService implements UUIDPort {
    private static final TimeBasedEpochGenerator INSTANCE = Generators.timeBasedEpochGenerator();

    @Override
    public UUID generateUUID() {
        return INSTANCE.generate();
    }

    @Override
    public UUID fromString(String uuid) {
        return UUID.fromString(uuid);
    }
}
