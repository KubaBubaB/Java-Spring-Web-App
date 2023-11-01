package com.example.AUIlab.functions;

import com.example.AUIlab.EntityClasses.Job;
import com.example.AUIlab.dto.GetJobsResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
@Component
public class JobsToResponseFunction implements Function<List<Job>, GetJobsResponse> {
    @Override
    public GetJobsResponse apply(List<Job> jobs){
        return GetJobsResponse.builder().jobs(jobs.stream().map(job -> GetJobsResponse.Job.builder()
                .id(job.getId()).name(job.getName()).build()).toList()).build();
    }
}
