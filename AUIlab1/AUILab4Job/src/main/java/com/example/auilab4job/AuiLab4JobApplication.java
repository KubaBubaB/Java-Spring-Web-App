package com.example.auilab4job;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AuiLab4JobApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuiLab4JobApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(@Value("${aui4.person.url}") String baseUrl) {
        return new RestTemplateBuilder().rootUri(baseUrl).build();
    }

}
