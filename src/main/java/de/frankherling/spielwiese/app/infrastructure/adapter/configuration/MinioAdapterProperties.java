package de.frankherling.spielwiese.app.infrastructure.adapter.configuration;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "minio")
@Data
@Validated
public class MinioAdapterProperties {

    @NotEmpty
    private String endpoint;
    @NotEmpty
    private String accessKey;
    @NotEmpty
    private String secretKey;

    @NotEmpty
    private String bucket;


}
