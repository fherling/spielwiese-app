package de.frankherling.spielwiese.app.application.service;

import de.frankherling.spielwiese.app.application.port.in.FileStorageInPort;
import de.frankherling.spielwiese.app.application.port.out.FileStorageOutPort;
import de.frankherling.spielwiese.app.infrastructure.adapter.configuration.MinioAdapterProperties;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class MinoFilestorageService implements FileStorageInPort, FileStorageOutPort {
    private final MinioAdapterProperties minioProperties;
    private final MinioClient minioClient;

    @Override
    public void uploadFile(MultipartFile multipartFile) {
        try {
            var putObjectRequest = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .object(multipartFile.getOriginalFilename())
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .contentType(multipartFile.getContentType())
                    .build();

            minioClient.putObject(putObjectRequest);

        } catch (Exception ex) {
            log.error("error [{}] occurred while uploading file.", ex.getMessage());
            throw new FileStorageException("Could not upload file");
        }
    }

    @Override
    public InputStreamResource downloadFile(String fileName) {
        try {
            var inputStream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(minioProperties.getBucket())
                    .object(fileName)
                    .build());
            return new InputStreamResource(inputStream);
        } catch (Exception ex) {
            log.error("error [{}] occurred while download [{}] ", ex.getMessage(), fileName);
            throw new FileStorageException("Error: Could not download file");
        }

    }

    @Override
    public void deleteFile(String fileName) {
        
    }
}
