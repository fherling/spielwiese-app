package de.frankherling.spielwiese.app.infrastructure.adapter.configuration;

import io.minio.MinioClient;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class MinioConfig {

    private final MinioAdapterProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        MinioClient client = MinioClient.builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();

        try {
            List<Bucket> buckets = client.listBuckets();
            buckets.forEach(bucket -> log.info("bucket [{}] created at [{}]", bucket.name(), bucket.creationDate()));

        } catch (Exception e) {
            log.error("error [{}] occurred while listing buckets.", e.getMessage());
            throw new RuntimeException(e);
        }

        return client;

    }
}
