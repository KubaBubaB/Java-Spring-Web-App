package com.example.auilab4job.function;

import com.example.auilab4job.dto.GetJobsResponse;
import com.example.auilab4job.entity.Job;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class JobsToResponseFunction  implements Function<List<Job>, GetJobsResponse> {
    @Override
    public GetJobsResponse apply(List<Job> jobs){
        System.out.println("GettingJobs");
        return GetJobsResponse.builder().jobs(jobs.stream().map(job -> GetJobsResponse.Job.builder()
                .id(job.getId()).name(job.getName()).build()).toList()).build();
    }
}
