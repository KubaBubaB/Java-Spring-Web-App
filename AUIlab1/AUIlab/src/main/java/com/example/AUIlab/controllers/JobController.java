package com.example.AUIlab.controllers;

import com.example.AUIlab.Services.JobService;
import com.example.AUIlab.dto.GetJobResponse;
import com.example.AUIlab.dto.GetJobsResponse;
import com.example.AUIlab.dto.PatchJobRequest;
import com.example.AUIlab.dto.PutJobRequest;
import com.example.AUIlab.functions.JobToResponseFunction;
import com.example.AUIlab.functions.JobsToResponseFunction;
import com.example.AUIlab.functions.RequestToJobFunction;
import com.example.AUIlab.functions.UpdateJobWithRequestFunction;
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
        jobService.create(requestToJobFunction.apply(id, request));
    }

    @PatchMapping("/jobs/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    private void updateJob(@PathVariable UUID id, @RequestBody PatchJobRequest request){
        jobService.find(id).ifPresentOrElse(job -> jobService.update(updateJobWithRequestFunction.apply(job, request)), () -> {
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
