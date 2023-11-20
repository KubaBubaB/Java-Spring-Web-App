package com.example.auilab4job.function;

import com.example.auilab4job.entity.Job;
import com.example.auilab4job.dto.GetJobResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class JobToResponseFunction implements Function<Job, GetJobResponse> {
    @Override
    public GetJobResponse apply(Job job){
        return GetJobResponse.builder().id(job.getId())
                .name(job.getName()).workHoursPerDay(job.getWorkHoursPerDay()).build();
    }
}
