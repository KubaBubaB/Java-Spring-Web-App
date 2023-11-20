package com.example.auilab4job.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Repository
public class JobEventRestRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public JobEventRestRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void delete(UUID id) {
        restTemplate.delete("/work/jobs/{id}", id);
    }

    public void create(UUID id, String name) {
        restTemplate.put("/work/jobs/{id}", name, id);
    }


}
