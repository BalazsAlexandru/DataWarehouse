package ro.uvt.info.dw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@SpringBootApplication()
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class AstraDbApp {

    // Connecting the Spring Boot application to an Astra DB is detailed below
    // https://medium.com/building-the-open-data-stack/build-a-goodreads-clone-with-spring-boot-and-astra-db-part-4-ed2dc22537e
    // in the 'Connecting the Spring Boot application to Astra DB' section
    public static void main(String[] args) {
        SpringApplication
            .run(AstraDbApp.class, args);
    }

    // This is necessary to have the Spring Boot app use the Astra secure bundle
    // to connect to the database
    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }
}