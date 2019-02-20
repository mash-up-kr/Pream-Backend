package com.mashup6th.preambackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class PreamBackendApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application-config.properties,"
            + "classpath:application-core.properties,"
            + "classpath:application-dev.properties,"
            + "classpath:application-rds.properties";

    public static void main(String[] args) {
        new SpringApplicationBuilder(PreamBackendApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("100Mb");
        return factory.createMultipartConfig();
    }
}

