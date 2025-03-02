package de.frankherling.spielwiese.app.application.port.out;

import org.springframework.core.io.InputStreamResource;

public interface FileStorageOutPort {
    InputStreamResource downloadFile(String fileName);

    void deleteFile(String fileName);
}
