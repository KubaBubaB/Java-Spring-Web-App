package com.example.auigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AuiGatewayApplication {



    public static void main(String[] args) {
        SpringApplication.run(AuiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder,
                                     @Value("${aui.person.url}") String personUrl,
                                     @Value("${aui.job.url}") String jobUrl,
                                     @Value("${aui.gateway.host}") String host){
        System.out.println("routinglog here");
        return builder.routes().
                route("jobs", r -> r.host(host).and().path("/work/jobs", "/work/jobs/{id}", "/work/jobs/workingHours/{workingHours}", "/work/jobs/*").uri(jobUrl)).
                route("persons", r -> r.host(host).and().path("/work/persons", "/work/persons/{id}", "/work/persons/jobs/{id}", "/work/persons/salary/{salary}", "/work/persons/*").uri(personUrl)).build();
    }


}
