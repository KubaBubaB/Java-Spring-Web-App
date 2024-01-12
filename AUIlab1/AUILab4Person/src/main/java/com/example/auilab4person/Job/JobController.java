package com.example.auilab4person.Job;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@Log
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PutMapping("/work/jobs/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    private void createJob(@PathVariable UUID id, @RequestBody String name){
        jobService.create(Job.builder().id(id).name(name).build());
    }
    @DeleteMapping("/work/jobs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJob(@PathVariable UUID id){
        jobService.find(id).ifPresentOrElse(job -> jobService.delete(id), () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }
}
