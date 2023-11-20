package com.example.auilab4job.function;

import com.example.auilab4job.dto.PatchJobRequest;
import com.example.auilab4job.entity.Job;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class UpdateJobWithRequestFunction implements BiFunction<Job, PatchJobRequest, Job> {
    @Override
    public Job apply(Job job, PatchJobRequest patchJobRequest) {
        return Job.builder().id(job.getId())
                .name(patchJobRequest.getName() == null ? job.getName() : patchJobRequest.getName())
                .workHoursPerDay(patchJobRequest.getWorkingHoursPerDay() == null ? job.getWorkHoursPerDay() : patchJobRequest.getWorkingHoursPerDay())
                .build();
    }
}
