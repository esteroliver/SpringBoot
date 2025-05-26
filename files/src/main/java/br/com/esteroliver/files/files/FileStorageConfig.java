package br.com.esteroliver.files.files;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {
    private String uploudDirectory;
}
