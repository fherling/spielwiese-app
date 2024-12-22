package de.frankherling.spielwiese.app.application.port.utils;

import java.util.UUID;

public interface UUIDPort {

    UUID generateUUID();

    UUID fromString(String uuid);
}
