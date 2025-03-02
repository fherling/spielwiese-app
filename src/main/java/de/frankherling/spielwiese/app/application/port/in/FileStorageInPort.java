package de.frankherling.spielwiese.app.application.port.in;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageInPort {
    void uploadFile(MultipartFile multipartFile);
}
