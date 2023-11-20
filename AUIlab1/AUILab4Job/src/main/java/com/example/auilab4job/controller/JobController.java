package com.example.auilab4job.controller;

import com.example.auilab4job.function.JobsToResponseFunction;
import com.example.auilab4job.function.RequestToJobFunction;
import com.example.auilab4job.function.UpdateJobWithRequestFunction;
import com.example.auilab4job.service.JobService;
import com.example.auilab4job.dto.GetJobResponse;
import com.example.auilab4job.dto.GetJobsResponse;
import com.example.auilab4job.dto.PatchJobRequest;
import com.example.auilab4job.dto.PutJobRequest;
import com.example.auilab4job.function.JobToResponseFunction;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/work")
@Log
public class JobController {
    private final JobService jobService;
    private  final JobToResponseFunction jobToResponseFunction;

    private final JobsToResponseFunction jobsToResponseFunction;

    private final RequestToJobFunction requestToJobFunction;

    private final UpdateJobWithRequestFunction updateJobWithRequestFunction;

    @Autowired
    public JobController(JobService jobService, JobToResponseFunction jobToResponseFunction, JobsToResponseFunction jobsToResponseFunction, RequestToJobFunction requestToJobFunction, UpdateJobWithRequestFunction updateJobWithRequestFunction) {
        this.jobService = jobService;
        this.jobToResponseFunction = jobToResponseFunction;
        this.jobsToResponseFunction = jobsToResponseFunction;
        this.requestToJobFunction = requestToJobFunction;
        this.updateJobWithRequestFunction = updateJobWithRequestFunction;
    }

    @GetMapping("/jobs")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private GetJobsResponse getJobsResponse(){
        return jobsToResponseFunction.apply(jobService.findAll());
    }

    @GetMapping("/jobs/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private GetJobResponse getJobResponse(@PathVariable UUID id){
        return jobService.find(id)
                .map(jobToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/jobs/workingHours/{workingHours}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private GetJobsResponse getJobsResponse(@PathVariable int workingHours){
        if(workingHours < 0 || workingHours > 24)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return jobsToResponseFunction.apply(jobService.findAllByWorkHoursPerDay(workingHours));
    }

    @PutMapping("/jobs/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    private void createJob(@PathVariable UUID id, @RequestBody PutJobRequest request){
        if(jobService.find(id).isPresent())
            throw new ResponseStatusException(HttpStatus.CREATED);
        jobService.create(requestToJobFunction.apply(id, request));
    }

    @PatchMapping("/jobs/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private void updateJob(@PathVariable UUID id, @RequestBody PatchJobRequest request){
        jobService.find(id).map(job -> updateJobWithRequestFunction.apply(job, request))
                .ifPresentOrElse(jobService::update, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
    }

    @DeleteMapping("/jobs/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteJob(@PathVariable UUID id){
        jobService.find(id).ifPresentOrElse(job -> jobService.delete(job.getId()), () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }
}
