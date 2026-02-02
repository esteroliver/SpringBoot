package br.com.esteroliver.files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.esteroliver.files.config.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageConfig.class
})
public class FilesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilesApplication.class, args);
    }

}
